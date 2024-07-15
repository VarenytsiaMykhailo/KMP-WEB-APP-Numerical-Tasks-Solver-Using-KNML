package presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.enter_the_function
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntegralFunctionInput(
    integralFunction: String,
    integralFunctionIncorrect: Boolean,
    onIntegralFunctionChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var value by remember(integralFunction) {
        mutableStateOf(integralFunction)
    }
    var isError by remember(integralFunction, integralFunctionIncorrect) {
        mutableStateOf(value.isBlank() || integralFunctionIncorrect)
    }
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            value = newValue

            isError = if (newValue.isNotBlank()) {
                onIntegralFunctionChanged(newValue)
                false
            } else {
                true
            }
        },
        label = { Text(stringResource(Res.string.enter_the_function)) },
        singleLine = true,
        isError = isError,
        modifier = modifier
            .fillMaxWidth(),
    )
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun IntegralMethodInputScreenPreview() {
    NumericalTasksSolverTheme {
        IntegralFunctionInput(
            integralFunction = "7 * cos(x + 5) - log10(x) - sqrt(x)",
            integralFunctionIncorrect = false,
            onIntegralFunctionChanged = {},
        )
    }
}

 */
