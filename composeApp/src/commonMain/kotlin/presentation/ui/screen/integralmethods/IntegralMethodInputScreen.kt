package presentation.ui.screen.integralmethods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.eps
import numericaltaskssolver.composeapp.generated.resources.solve_integral
import org.jetbrains.compose.resources.stringResource
import presentation.ui.component.DoubleValueInputField
import presentation.ui.component.FormSolutionCheckbox
import presentation.ui.component.IntegralFunctionInput
import presentation.ui.component.IntegralIntervalsInput

@Composable
fun IntegralMethodInputScreen(
    integralFunction: String,
    integralFunctionIncorrect: Boolean,
    intervalStart: Double,
    intervalEnd: Double,
    epsilonPrecision: Double,
    shouldFormFullSolution: Boolean,
    onIntegralFunctionChanged: (String) -> Unit,
    onIntervalStartChanged: (Double) -> Unit,
    onIntervalEndChanged: (Double) -> Unit,
    onEpsilonPrecisionUpdated: ((Double) -> Unit),
    onShouldFormFullSolutionChanged: (Boolean) -> Unit,
    onSolveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        IntegralFunctionInput(
            integralFunction = integralFunction,
            integralFunctionIncorrect = integralFunctionIncorrect,
            onIntegralFunctionChanged = onIntegralFunctionChanged,
        )
        Spacer(modifier = Modifier.height(16.dp))
        IntegralIntervalsInput(
            intervalStart = intervalStart,
            intervalEnd = intervalEnd,
            onIntervalStartChanged = onIntervalStartChanged,
            onIntervalEndChanged = onIntervalEndChanged,
        )
        Spacer(modifier = Modifier.height(16.dp))
        DoubleValueInputField(
            value = epsilonPrecision,
            minValue = 0.0000001,
            maxValue = 1.0,
            label = Res.string.eps,
            onValueUpdated = onEpsilonPrecisionUpdated,
        )
        Spacer(modifier = Modifier.height(16.dp))
        FormSolutionCheckbox(
            shouldFormFullSolution = shouldFormFullSolution,
            onFormFullSolutionChanged = onShouldFormFullSolutionChanged,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSolveButtonClick,
            modifier = modifier
                .widthIn(min = 250.dp),
        ) {
            Text(
                text = stringResource(Res.string.solve_integral),
            )
        }
    }
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun IntegralMethodInputScreenPreview() {
    NumericalTasksSolverTheme {
        IntegralMethodInputScreen(
            integralFunction = "7 * cos(x + 5) - log10(x) - sqrt(x)",
            integralFunctionIncorrect = false,
            intervalStart = -20.0,
            intervalEnd = 100.0,
            epsilonPrecision = 0.001,
            shouldFormFullSolution = true,
            onIntegralFunctionChanged = {},
            onIntervalStartChanged = {},
            onIntervalEndChanged = {},
            onEpsilonPrecisionUpdated = {},
            onShouldFormFullSolutionChanged = {},
            onSolveButtonClick = {},
        )
    }
}

 */
