package presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.eps
import numericaltaskssolver.composeapp.generated.resources.use_machine_eps
import org.jetbrains.compose.resources.stringResource

@Composable
fun EpsilonPrecisionInput(
    epsilonPrecision: Double?,
    onEpsilonPrecisionUpdated: (Double?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        var checked by remember { mutableStateOf(false) }

        MachineEpsilonPrecisionSwitch(
            checked = checked,
            onCheckedChange = {
                checked = it
                if (it) {
                    onEpsilonPrecisionUpdated(null)
                } else {
                    onEpsilonPrecisionUpdated(0.001)
                }
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        if (!checked && epsilonPrecision != null) {
            DoubleValueInputField(
                value = epsilonPrecision,
                minValue = 0.0000000001,
                maxValue = 1.0,
                label = Res.string.eps,
                onValueUpdated = onEpsilonPrecisionUpdated,
            )
        }
    }
}

@Composable
private fun MachineEpsilonPrecisionSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
        Text(text = stringResource(Res.string.use_machine_eps))
    }
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun EpsilonPrecisionInputPreview() {
    NumericalTasksSolverTheme {
        EpsilonPrecisionInput(
            epsilonPrecision = 0.001,
            onEpsilonPrecisionUpdated = {},
        )
    }
}

 */
