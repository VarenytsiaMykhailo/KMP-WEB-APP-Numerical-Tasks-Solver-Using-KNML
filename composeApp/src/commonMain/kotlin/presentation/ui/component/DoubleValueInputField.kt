package presentation.ui.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DoubleValueInputField(
    value: Double,
    minValue: Double,
    maxValue: Double,
    label: StringResource,
    onValueUpdated: (Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    var valueRemembered by remember(value) {
        mutableStateOf(value.toString())
    }
    var isError by remember(valueRemembered) {
        mutableStateOf(valueRemembered.isBlank())
    }
    OutlinedTextField(
        value = valueRemembered,
        onValueChange = { newValue ->
            valueRemembered = newValue

            val doubleValue = newValue.toDoubleOrNull()
            isError = if (doubleValue != null && doubleValue > minValue && doubleValue < maxValue) {
                onValueUpdated(doubleValue)
                false
            } else {
                true
            }
        },
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        singleLine = true,
        isError = isError,
        modifier = modifier
            .width(120.dp),
    )
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun DoubleValueInputFieldPreview() {
    NumericalTasksSolverTheme {
        DoubleValueInputField(
            value = 0.001,
            minValue = 0.0000000001,
            maxValue = 1.0,
            label = R.string.eps,
            onValueUpdated = {},
        )
    }
}

 */