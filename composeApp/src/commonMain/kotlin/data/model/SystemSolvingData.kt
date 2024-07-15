package data.model

import kotlinx.serialization.Serializable

@Serializable
data class SystemSolvingData(
    val methodType: String,
    val matrixAElements: String,
    val matrixARows: Int,
    val matrixAColumns: Int,
    val vectorBElements: String,
    val vectorBSize: Int,
    val vectorIElements: String? = null,
    val vectorIElementsSize: Int? = null,
    val gaussMethodPivotingStrategy: String? = null,
    val eps: Double? = null,
    val formSolution: Boolean,
)
