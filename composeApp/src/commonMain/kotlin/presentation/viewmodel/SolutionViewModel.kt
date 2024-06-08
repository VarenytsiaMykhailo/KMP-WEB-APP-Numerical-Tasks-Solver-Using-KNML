package presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SolutionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SolutionState())
    val uiState: StateFlow<SolutionState> = _uiState.asStateFlow()

    fun updateSolution(
        solutionString: String,
    ) {
        _uiState.update {
            it.copy(
                solutionString = solutionString,
            )
        }
    }
}

data class SolutionState(
    val solutionString: String = "",
)
