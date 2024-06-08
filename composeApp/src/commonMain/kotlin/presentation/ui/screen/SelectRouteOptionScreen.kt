package presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.choose_method_type
import numericaltaskssolver.composeapp.generated.resources.integral_methods
import numericaltaskssolver.composeapp.generated.resources.system_solving_methods
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.navigation.route.RootScreenRoutes
import presentation.navigation.route.Route

@Composable
fun SelectRouteOptionScreen(
    textResourceId: StringResource,
    routeButtons: List<Pair<Route, StringResource>>,
    onRouteButtonClick: (routeName: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(textResourceId),
            style = MaterialTheme.typography.headlineLarge,
        )
        routeButtons.forEach { item ->
            Button(
                onClick = {
                    onRouteButtonClick(item.first.routeName)
                },
                modifier = modifier
                    .widthIn(min = 250.dp),
            ) {
                Text(
                    text = stringResource(item.second),
                )
            }
        }
    }
}

@Preview()
@Composable
fun SelectRouteOptionScreenPreview() {
    MaterialTheme {
        SelectRouteOptionScreen(
            textResourceId = Res.string.choose_method_type,
            routeButtons = listOf(
                Pair(RootScreenRoutes.SystemSolvingMethods, Res.string.system_solving_methods),
                Pair(RootScreenRoutes.IntegralMethods, Res.string.integral_methods),
            ),
            onRouteButtonClick = {},
        )
    }
}
