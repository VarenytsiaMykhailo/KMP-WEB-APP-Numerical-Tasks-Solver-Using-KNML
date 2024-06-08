package presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.integral_methods
import numericaltaskssolver.composeapp.generated.resources.query_method
import numericaltaskssolver.composeapp.generated.resources.system_solving_methods
import presentation.navigation.route.RootScreenRoutes
import presentation.navigation.route.SELECT_ROUTE_OPTION_SCREEN
import presentation.ui.screen.RootScreen
import presentation.viewmodel.QueryMethodViewModel
import presentation.viewmodel.SolutionViewModel


fun NavGraphBuilder.addRootScreenRoutes(
    //systemSolvingMethodViewModel: SystemSolvingMethodViewModel,
    //integralMethodViewModel: IntegralMethodViewModel,
    queryMethodViewModel: QueryMethodViewModel,
    solutionViewModel: SolutionViewModel,
    navController: NavHostController,
    rootScreen: String
) {
    navigation(
        route = rootScreen,
        startDestination = SELECT_ROUTE_OPTION_SCREEN,
    ) {
        composable(route = SELECT_ROUTE_OPTION_SCREEN) {
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
        /*
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
         */
        addOtherScreenRoutes(
            queryMethodViewModel = queryMethodViewModel,
            solutionViewModel = solutionViewModel,
            navController = navController,
        )
    }
}
