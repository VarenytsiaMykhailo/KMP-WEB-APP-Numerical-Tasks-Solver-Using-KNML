package presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.choose_method
import numericaltaskssolver.composeapp.generated.resources.rectangle_method
import numericaltaskssolver.composeapp.generated.resources.simpson_method
import numericaltaskssolver.composeapp.generated.resources.trapezoid_method
import presentation.navigation.route.IntegralMethodsScreenRoutes
import presentation.navigation.route.OtherScreenRoutes
import presentation.navigation.route.RootScreenRoutes
import presentation.ui.screen.SelectRouteOptionScreen
import presentation.ui.screen.integralmethods.IntegralMethodInputScreen
import presentation.viewmodel.IntegralMethodViewModel
import presentation.viewmodel.SolutionViewModel

fun NavGraphBuilder.addIntegralMethodsScreenRoutes(
    integralMethodViewModel: IntegralMethodViewModel,
    solutionViewModel: SolutionViewModel,
    navController: NavHostController
) {
    navigation(
        route = RootScreenRoutes.IntegralMethods.routeName,
        startDestination = OtherScreenRoutes.SelectRouteOptionScreen.routeName,
    ) {
        composable(route = OtherScreenRoutes.SelectRouteOptionScreen.routeName) {
            SelectRouteOptionScreen(
                textResourceId = Res.string.choose_method,
                routeButtons = listOf(
                    Pair(
                        IntegralMethodsScreenRoutes.SimpsonMethod,
                        Res.string.simpson_method,
                    ),
                    Pair(
                        IntegralMethodsScreenRoutes.TrapezoidMethod,
                        Res.string.trapezoid_method,
                    ),
                    Pair(
                        IntegralMethodsScreenRoutes.RectangleMethod,
                        Res.string.rectangle_method,
                    ),
                ),
                onRouteButtonClick = {
                    navController.navigate(it)
                },
            )
        }
        composable(route = IntegralMethodsScreenRoutes.SimpsonMethod.routeName) {
            val uiState by integralMethodViewModel.uiState.collectAsState()
            IntegralMethodInputScreen(
                integralFunction = uiState.integralFunction,
                integralFunctionIncorrect = uiState.integralFunctionIncorrect,
                intervalStart = uiState.intervalStart,
                intervalEnd = uiState.intervalEnd,
                epsilonPrecision = uiState.epsilonPrecision,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onIntegralFunctionChanged = {
                    integralMethodViewModel.updateIntegralFunction(it)
                },
                onIntervalStartChanged = {
                    integralMethodViewModel.updateIntervalStart(it)
                },
                onIntervalEndChanged = {
                    integralMethodViewModel.updateIntervalEnd(it)
                },
                onEpsilonPrecisionUpdated = {
                    integralMethodViewModel.updateEpsilonPrecision(it)
                },
                onShouldFormFullSolutionChanged = {
                    integralMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    integralMethodViewModel.solveIntegral(
                        methodType = IntegralMethodViewModel.MethodType.SIMPSON_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                },
            )
        }
        composable(route = IntegralMethodsScreenRoutes.TrapezoidMethod.routeName) {
            val uiState by integralMethodViewModel.uiState.collectAsState()
            IntegralMethodInputScreen(
                integralFunction = uiState.integralFunction,
                integralFunctionIncorrect = uiState.integralFunctionIncorrect,
                intervalStart = uiState.intervalStart,
                intervalEnd = uiState.intervalEnd,
                epsilonPrecision = uiState.epsilonPrecision,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onIntegralFunctionChanged = {
                    integralMethodViewModel.updateIntegralFunction(it)
                },
                onIntervalStartChanged = {
                    integralMethodViewModel.updateIntervalStart(it)
                },
                onIntervalEndChanged = {
                    integralMethodViewModel.updateIntervalEnd(it)
                },
                onEpsilonPrecisionUpdated = {
                    integralMethodViewModel.updateEpsilonPrecision(it)
                },
                onShouldFormFullSolutionChanged = {
                    integralMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    integralMethodViewModel.solveIntegral(
                        methodType = IntegralMethodViewModel.MethodType.TRAPEZOID_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                },
            )
        }
        composable(route = IntegralMethodsScreenRoutes.RectangleMethod.routeName) {
            val uiState by integralMethodViewModel.uiState.collectAsState()
            IntegralMethodInputScreen(
                integralFunction = uiState.integralFunction,
                integralFunctionIncorrect = uiState.integralFunctionIncorrect,
                intervalStart = uiState.intervalStart,
                intervalEnd = uiState.intervalEnd,
                epsilonPrecision = uiState.epsilonPrecision,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onIntegralFunctionChanged = {
                    integralMethodViewModel.updateIntegralFunction(it)
                },
                onIntervalStartChanged = {
                    integralMethodViewModel.updateIntervalStart(it)
                },
                onIntervalEndChanged = {
                    integralMethodViewModel.updateIntervalEnd(it)
                },
                onEpsilonPrecisionUpdated = {
                    integralMethodViewModel.updateEpsilonPrecision(it)
                },
                onShouldFormFullSolutionChanged = {
                    integralMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    integralMethodViewModel.solveIntegral(
                        methodType = IntegralMethodViewModel.MethodType.RECTANGLE_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                },
            )
        }
    }
}
