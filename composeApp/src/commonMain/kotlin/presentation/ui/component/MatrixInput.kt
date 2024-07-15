package presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
fun MatrixInput(
    label: String,
    rows: Int,
    columns: Int,
    data: List<List<Double>>,
    onDataChanged: (List<List<Double>>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            items(columns) { column ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    repeat(rows) { row ->
                        var value by remember(data) {
                            mutableStateOf(data.getOrNull(row)?.getOrNull(column)?.toString() ?: "")
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
                                            data[row].update(
                                                column,
                                                doubleValue,
                                            )
                                        )
                                    )
                                    false
                                } else {
                                    true
                                }
                            },
                            label = { Text("${label.first().lowercaseChar()}${row}${column}") },
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
    }
}

private fun <T> List<T>.update(index: Int, item: T) =
    toMutableList().apply { this[index] = item }.toList()

/*
@Preview(
    showSystemUi = true,
)
@Composable
fun MatrixInputPreview() {
    NumericalTasksSolverTheme {
        MatrixInput(
            label = "A = ",
            rows = 4,
            columns = 3,
            data = listOf(
                listOf(1.0, 2.0, 3.0),
                listOf(4.0, 5.0, 6.0),
                listOf(7.0, 8.0, 9.0),
            ),
            onDataChanged = {},
        )
    }
}

 */
