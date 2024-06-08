package presentation.ui.screen.querymethod

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.solve
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun QueryMethodInputScreen(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSolveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newValue ->
                onQueryChanged(newValue)
            },
            label = {
                Text(
                    //text = "jacobi method {20.0, -40.0, 28.0} {{115.0, -20.0, -75.0}, {15.0, -50.0, -5.0}, {6.0, 2.0, 20.0}}",
                    text = ""
                )
            },
            //singleLine = true,
            //modifier = modifier.width(80.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSolveButtonClick,
            modifier = modifier
                .widthIn(min = 250.dp),
        ) {
            Text(
                text = stringResource(Res.string.solve),
            )
        }
    }
}

@Preview()
@Composable
fun QueryMethodInputScreenPreview() {
    MaterialTheme {
        QueryMethodInputScreen(
            query = "",
            onQueryChanged = {},
            onSolveButtonClick = {},
        )
    }
}
