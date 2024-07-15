package presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.integral_methods
import numericaltaskssolver.composeapp.generated.resources.query_method
import numericaltaskssolver.composeapp.generated.resources.system_solving_methods
import presentation.navigation.route.NavigationStartRouteGroup
import presentation.navigation.route.RootScreenRoutes
import presentation.ui.screen.RootScreen
import presentation.viewmodel.IntegralMethodViewModel
import presentation.viewmodel.QueryMethodViewModel
import presentation.viewmodel.SolutionViewModel
import presentation.viewmodel.SystemSolvingMethodViewModel

fun NavGraphBuilder.addRootScreenRoutes(
    systemSolvingMethodViewModel: SystemSolvingMethodViewModel,
    integralMethodViewModel: IntegralMethodViewModel,
    queryMethodViewModel: QueryMethodViewModel,
    solutionViewModel: SolutionViewModel,
    navController: NavHostController,
    navigationStartRouteGroup: String,
) {
    navigation(
        route = navigationStartRouteGroup,
        startDestination = NavigationStartRouteGroup.RootScreen.routeName,
    ) {
        composable(route = NavigationStartRouteGroup.RootScreen.routeName) {
            RootScreen(
                routeButtons = listOf(
                    Pair(RootScreenRoutes.SystemSolvingMethods, Res.string.system_solving_methods),
                    Pair(RootScreenRoutes.IntegralMethods, Res.string.integral_methods),
                    Pair(RootScreenRoutes.QueryMethod, Res.string.query_method),
                ),
                onRouteButtonClick = {
                    navController.navigate(it)
                }
            )
        }
        addSystemSolvingMethodsScreenRoutes(
            systemSolvingMethodViewModel = systemSolvingMethodViewModel,
            solutionViewModel = solutionViewModel,
            navController = navController,
        )
        addIntegralMethodsScreenRoutes(
            integralMethodViewModel = integralMethodViewModel,
            solutionViewModel = solutionViewModel,
            navController = navController,
        )
        addOtherScreenRoutes(
            queryMethodViewModel = queryMethodViewModel,
            solutionViewModel = solutionViewModel,
            navController = navController,
        )
    }
}
