package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QueryMethodViewModel : ViewModel() {

    private val repository = Repository()

    private val _uiState = MutableStateFlow(QueryMethodState())
    val uiState: StateFlow<QueryMethodState> = _uiState.asStateFlow()

    fun updateQuery(
        query: String,
    ) {
        _uiState.update {
            it.copy(
                query = query,
            )
        }
    }

    fun makeQuery(
        onSuccess: (solutionString: String) -> Unit,
    ) {
        viewModelScope.launch {

            val result = repository.solveWithQueryMethod(
                query = _uiState.value.query,
            )

            onSuccess(result)
        }
    }
}

data class QueryMethodState(
    val query: String = "jacobi method {20.0, -40.0, 28.0} {{115.0, -20.0, -75.0}, {15.0, -50.0, -5.0}, {6.0, 2.0, 20.0}}",
)