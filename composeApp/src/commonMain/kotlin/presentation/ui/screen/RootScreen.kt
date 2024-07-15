package presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.app_logo
import numericaltaskssolver.composeapp.generated.resources.choose_method_type
import numericaltaskssolver.composeapp.generated.resources.integral_methods
import numericaltaskssolver.composeapp.generated.resources.system_solving_methods
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.navigation.route.RootScreenRoutes
import presentation.navigation.route.Route

@Composable
fun RootScreen(
    routeButtons: List<Pair<Route, StringResource>>,
    onRouteButtonClick: (routeName: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(Res.drawable.app_logo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(360.dp)
                .fillMaxWidth(),
        )
        SelectRouteOptionScreen(
            textResourceId = Res.string.choose_method_type,
            routeButtons = routeButtons,
            onRouteButtonClick = onRouteButtonClick,
        )
    }
}

@Preview()
@Composable
fun RootScreenPreview() {
    MaterialTheme {
        RootScreen(
            routeButtons = listOf(
                Pair(RootScreenRoutes.SystemSolvingMethods, Res.string.system_solving_methods),
                Pair(RootScreenRoutes.IntegralMethods, Res.string.integral_methods),
            ),
            onRouteButtonClick = {}
        )
    }
}
