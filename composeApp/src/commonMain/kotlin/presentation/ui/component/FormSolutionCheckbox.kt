package presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.form_full_solution
import org.jetbrains.compose.resources.stringResource

@Composable
fun FormSolutionCheckbox(
    shouldFormFullSolution: Boolean,
    onFormFullSolutionChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = stringResource(Res.string.form_full_solution),
        )
        Checkbox(
            checked = shouldFormFullSolution,
            onCheckedChange = {
                onFormFullSolutionChanged(it)
            },
        )
    }
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun FormSolutionCheckboxPreview() {
    NumericalTasksSolverTheme {
        FormSolutionCheckbox(
            shouldFormFullSolution = false,
            onFormFullSolutionChanged = {},
        )
    }
}

 */
