package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntegralMethodViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IntegralMethodInputState())
    val uiState: StateFlow<IntegralMethodInputState> = _uiState.asStateFlow()

    fun updateIntegralFunction(integralFunction: String) {
        _uiState.update {
            it.copy(
                integralFunction = integralFunction,
                integralFunctionIncorrect = false,
            )
        }
    }

    private fun updateIntegralFunctionIncorrect(integralFunctionIncorrect: Boolean) {
        _uiState.update {
            it.copy(
                integralFunctionIncorrect = integralFunctionIncorrect,
            )
        }
    }

    fun updateIntervalStart(intervalStart: Double) {
        _uiState.update {
            it.copy(
                intervalStart = intervalStart,
            )
        }
    }

    fun updateIntervalEnd(intervalEnd: Double) {
        _uiState.update {
            it.copy(
                intervalEnd = intervalEnd,
            )
        }
    }

    fun updateEpsilonPrecision(epsilonPrecision: Double) {
        _uiState.update {
            it.copy(
                epsilonPrecision = epsilonPrecision,
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

    fun solveIntegral(
        methodType: MethodType,
        onSuccess: (solutionString: String) -> Unit,
    ) {
        /*
        viewModelScope.launch(Dispatchers.Default) {
            val uiState = _uiState.value

            val function: (Double) -> Double  = try {
                integralFunctionStringToLambda(uiState.integralFunction)
            } catch (e: Exception) {
                updateIntegralFunctionIncorrect(true)
                return@launch
            }

            val result = when (methodType) {
                MethodType.SIMPSON_METHOD ->
                    SimpsonMethod().solveIntegralBySimpsonMethod(
                        intervalStart = uiState.intervalStart,
                        intervalEnd = uiState.intervalEnd,
                        eps = uiState.epsilonPrecision,
                        formSolution = uiState.shouldFormFullSolution,
                        integralFunction = function,
                    )
                MethodType.TRAPEZOID_METHOD ->
                    TrapezoidMethod().solveIntegralByTrapezoidMethod(
                        intervalStart = uiState.intervalStart,
                        intervalEnd = uiState.intervalEnd,
                        eps = uiState.epsilonPrecision,
                        formSolution = uiState.shouldFormFullSolution,
                        integralFunction = function,
                    )
                MethodType.RECTANGLE_METHOD ->
                    RectangleMethod().solveIntegralByRectangleMethod(
                        intervalStart = uiState.intervalStart,
                        intervalEnd = uiState.intervalEnd,
                        eps = uiState.epsilonPrecision,
                        formSolution = uiState.shouldFormFullSolution,
                        integralFunction = function,
                    )
            }

            println("res = $result")
            withContext(Dispatchers.Main) {
                onSuccess(result.toSolution())
            }
        }

         */
    }

    /*
    private fun integralFunctionStringToLambda(
        integralFunction: String,
    ): (Double) -> Double {
        /*
        val functionExpression: Expression =
            ExpressionBuilder(integralFunction).variables("x").build()

        val function: (x: Double) -> Double = { x ->
            try {
                functionExpression.setVariable("x", x).evaluate()
            } catch (e: Throwable) {
                if (e.message == "Division by zero!") {
                    Double.POSITIVE_INFINITY
                } else {
                    Double.NaN
                }
            }
        }
        return function
         */
    }

     */

    enum class MethodType {
        SIMPSON_METHOD,
        TRAPEZOID_METHOD,
        RECTANGLE_METHOD,
    }
}

data class IntegralMethodInputState(
    val integralFunction: String = "7 * cos(x + 5) - log10(x) - sqrt(x)",
    val integralFunctionIncorrect: Boolean = false,
    val intervalStart: Double = 0.0,
    val intervalEnd: Double = 1.0,
    val epsilonPrecision: Double = 0.001,
    val shouldFormFullSolution: Boolean = false,
)