package presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun VectorInput(
    label: String,
    rows: Int,
    data: List<Double>,
    onDataChanged: (List<Double>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            repeat(rows) { row ->
                var value by remember(data) {
                    mutableStateOf(data.getOrNull(row)?.toString() ?: "")
                }
                var isError by remember(data) {
                    mutableStateOf(value.isBlank())
                }
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        value = newValue
                        val doubleValue = newValue.toDoubleOrNull()
                        isError = if (doubleValue != null) {
                            onDataChanged(
                                data.update(
                                    row,
                                    doubleValue,
                                )
                            )
                            false
                        } else {
                            true
                        }
                    },
                    label = { Text("${label.first().lowercaseChar()}${row}") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    isError = isError,
                    modifier = Modifier.width(80.dp),
                )
            }
        }
    }
}

private fun <T> List<T>.update(index: Int, item: T) =
    toMutableList().apply { this[index] = item }.toList()
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun VectorInputPreview() {
    NumericalTasksSolverTheme {
        VectorInput(
            label = "B = ",
            rows = 5,
            data = listOf(1.0, 2.0, 3.0),
            onDataChanged = {},
        )
    }
}

 */