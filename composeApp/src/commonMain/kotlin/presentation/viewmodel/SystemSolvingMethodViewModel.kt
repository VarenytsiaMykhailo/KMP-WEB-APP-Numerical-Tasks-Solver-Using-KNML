package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Repository
import data.model.SystemSolvingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SystemSolvingMethodViewModel : ViewModel() {

    private val repository = Repository()

    private val _uiState = MutableStateFlow(SystemSolvingMethodInputState())
    val uiState: StateFlow<SystemSolvingMethodInputState> = _uiState.asStateFlow()

    fun updateDimension(dimension: Int) {
        _uiState.update {
            it.copy(
                dimension = dimension,

                matrixA = buildList {
                    for (i in 0 until dimension) {
                        add(
                            buildListWithZeros(dimension)
                        )
                    }
                },
                vectorB = buildListWithZeros(dimension),
                vectorI = buildListWithZeros(dimension),
            )
        }
    }

    fun updateMatrixA(data: List<List<Double>>) {
        _uiState.update {
            it.copy(
                matrixA = data,
            )
        }
    }

    fun updateVectorB(data: List<Double>) {
        _uiState.update {
            it.copy(
                vectorB = data,
            )
        }
    }

    fun updateVectorI(data: List<Double>?) {
        _uiState.update {
            it.copy(
                vectorI = data,
            )
        }
    }

    fun updateEpsilonPrecision(epsilonPrecision: Double?) {
        _uiState.update {
            it.copy(
                epsilonPrecision = epsilonPrecision,
            )
        }
    }

    fun gaussMethodPivotingStrategiesList() =
        listOf(
            GaussMethodPivotingStrategy.Complete.name,
            GaussMethodPivotingStrategy.PartialByRow.name,
            GaussMethodPivotingStrategy.PartialByColumn.name,
        )

    fun updateGaussMethodPivotingStrategy(gaussMethodPivotingStrategy: String) {
        _uiState.update {
            it.copy(
                gaussMethodPivotingStrategy = when (gaussMethodPivotingStrategy) {
                    GaussMethodPivotingStrategy.Complete.name -> GaussMethodPivotingStrategy.Complete
                    GaussMethodPivotingStrategy.PartialByRow.name -> GaussMethodPivotingStrategy.PartialByRow
                    GaussMethodPivotingStrategy.PartialByColumn.name -> GaussMethodPivotingStrategy.PartialByColumn
                    else -> GaussMethodPivotingStrategy.Complete
                }
            )
        }
    }

    fun updateShouldFormFullSolution(shouldFormFullSolution: Boolean) {
        _uiState.update {
            it.copy(
                shouldFormFullSolution = shouldFormFullSolution,
            )
        }
    }

    fun solveSystem(
        methodType: MethodType,
        onSuccess: (solutionString: String) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            val uiState = _uiState.value
            var matrixAElements = ""
            uiState.matrixA.forEach { row ->
                row.forEach {
                    matrixAElements += "$it "
                }
            }
            matrixAElements = matrixAElements.trimEnd()

            var vectorBElements = ""
            uiState.vectorB.forEach {
                vectorBElements += "$it "
            }
            vectorBElements = vectorBElements.trimEnd()

            var vectorIElements: String? = null
            uiState.vectorI?.let {
                vectorIElements = ""
                it.forEach { elem ->
                    vectorIElements += "$elem "
                }
                vectorIElements = vectorIElements!!.trimEnd()
            }

            val systemSolvingData = SystemSolvingData(
                methodType = methodType.name,
                matrixAElements = matrixAElements,
                matrixARows = uiState.matrixA.size,
                matrixAColumns = uiState.matrixA.first().size,
                vectorBElements = vectorBElements,
                vectorBSize = uiState.vectorB.size,
                vectorIElements = vectorIElements,
                vectorIElementsSize = uiState.vectorI?.size,
                gaussMethodPivotingStrategy = uiState.gaussMethodPivotingStrategy.name,
                eps = uiState.epsilonPrecision,
                formSolution = uiState.shouldFormFullSolution,
            )
            println("formed systemSolvingData = $systemSolvingData")

            val result = repository.solveSystem(systemSolvingData)

            println("res = $result")
            withContext(Dispatchers.Main) {
                onSuccess(result)
            }
        }
    }

    private fun buildListWithZeros(dimension: Int) =
        buildList {
            for (j in 0 until dimension) {
                add(0.0)
            }
        }

    enum class MethodType {
        GAUSS_CLASSIC_METHOD,
        GAUSS_PIVOTING_METHOD,
        JACOBI_METHOD,
        SEIDEL_METHOD,
        THOMAS_METHOD,
    }
}

data class SystemSolvingMethodInputState(
    val dimension: Int = 3,
    val matrixA: List<List<Double>> = listOf(
        listOf(115.0, -20.0, -75.0),
        listOf(15.0, -50.0, -5.0),
        listOf(6.0, 2.0, 20.0),
    ),
    val vectorB: List<Double> = listOf(20.0, -40.0, 28.0),
    val vectorI: List<Double>? = listOf(1.0, 1.0, 1.0),
    val epsilonPrecision: Double? = 0.001,
    val shouldFormFullSolution: Boolean = false,
    val gaussMethodPivotingStrategy: GaussMethodPivotingStrategy =
        GaussMethodPivotingStrategy.Complete,
)

enum class GaussMethodPivotingStrategy {
    Complete,
    PartialByRow,
    PartialByColumn,
}
