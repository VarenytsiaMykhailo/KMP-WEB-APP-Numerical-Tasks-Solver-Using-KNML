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

@Composable
fun DimensionInput(
    dimension: Int,
    onDimensionUpdated: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var value by remember(dimension) {
        mutableStateOf(dimension.toString())
    }
    var isError by remember(dimension) {
        mutableStateOf(value.isBlank())
    }
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            value = newValue

            val intValue = newValue.toIntOrNull()
            isError = if (intValue != null && intValue >= 1 && intValue < 50) {
                onDimensionUpdated(intValue)
                false
            } else {
                true
            }
        },
        label = { Text("n = $dimension") },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        isError = isError,
        modifier = modifier.width(80.dp),
    )
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun DimensionInputPreview() {
    NumericalTasksSolverTheme {
        DimensionInput(
            dimension = 3,
            onDimensionUpdated = {},
        )
    }
}

 */
