package presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.interval_end
import numericaltaskssolver.composeapp.generated.resources.interval_start

@Composable
fun IntegralIntervalsInput(
    intervalStart: Double,
    intervalEnd: Double,
    onIntervalStartChanged: (Double) -> Unit,
    onIntervalEndChanged: (Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        DoubleValueInputField(
            value = intervalStart,
            minValue = Int.MIN_VALUE.toDouble(),
            maxValue = Int.MAX_VALUE.toDouble(),
            label = Res.string.interval_start,
            onValueUpdated = onIntervalStartChanged,
        )
        Spacer(modifier = Modifier.width(16.dp))
        DoubleValueInputField(
            value = intervalEnd,
            minValue = Int.MIN_VALUE.toDouble(),
            maxValue = Int.MAX_VALUE.toDouble(),
            label = Res.string.interval_end,
            onValueUpdated = onIntervalEndChanged,
        )
    }
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun IntegralIntervalsInputPreview() {
    NumericalTasksSolverTheme {
        IntegralIntervalsInput(
            intervalStart = 1.0,
            intervalEnd = 2.0,
            onIntervalStartChanged = {},
            onIntervalEndChanged = {},
        )
    }
}

 */
