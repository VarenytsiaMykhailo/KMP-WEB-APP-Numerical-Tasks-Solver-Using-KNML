package presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.varenytsiamykhailo.numericaltaskssolver.presentation.ui.screen.systemsolvingmethods.SystemSolvingMethodInputScreen
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.choose_method
import numericaltaskssolver.composeapp.generated.resources.gauss_classic_method
import numericaltaskssolver.composeapp.generated.resources.gauss_pivoting_method
import numericaltaskssolver.composeapp.generated.resources.jacobi_method
import numericaltaskssolver.composeapp.generated.resources.seidel_method
import numericaltaskssolver.composeapp.generated.resources.thomas_method
import presentation.navigation.route.OtherScreenRoutes
import presentation.navigation.route.RootScreenRoutes
import presentation.navigation.route.SystemSolvingMethodsScreenRoutes
import presentation.ui.screen.SelectRouteOptionScreen
import presentation.viewmodel.SolutionViewModel
import presentation.viewmodel.SystemSolvingMethodViewModel

fun NavGraphBuilder.addSystemSolvingMethodsScreenRoutes(
    systemSolvingMethodViewModel: SystemSolvingMethodViewModel,
    solutionViewModel: SolutionViewModel,
    navController: NavHostController,
) {
    navigation(
        route = RootScreenRoutes.SystemSolvingMethods.routeName,
        startDestination = OtherScreenRoutes.SelectRouteOptionScreen.routeName,
    ) {
        composable(route = OtherScreenRoutes.SelectRouteOptionScreen.routeName) {
            SelectRouteOptionScreen(
                textResourceId = Res.string.choose_method,
                routeButtons = listOf(
                    Pair(
                        SystemSolvingMethodsScreenRoutes.GaussClassicMethod,
                        Res.string.gauss_classic_method,
                    ),
                    Pair(
                        SystemSolvingMethodsScreenRoutes.GaussPivotingMethod,
                        Res.string.gauss_pivoting_method,
                    ),
                    Pair(
                        SystemSolvingMethodsScreenRoutes.SeidelMethod,
                        Res.string.seidel_method,
                    ),
                    Pair(
                        SystemSolvingMethodsScreenRoutes.JacobiMethod,
                        Res.string.jacobi_method,
                    ),
                    Pair(
                        SystemSolvingMethodsScreenRoutes.ThomasMethod,
                        Res.string.thomas_method,
                    ),
                ),
                onRouteButtonClick = {
                    navController.navigate(it)
                },
            )
        }
        composable(route = SystemSolvingMethodsScreenRoutes.GaussClassicMethod.routeName) {
            val uiState by systemSolvingMethodViewModel.uiState.collectAsState()
            SystemSolvingMethodInputScreen(
                dimension = uiState.dimension,
                matrixA = uiState.matrixA,
                vectorB = uiState.vectorB,
                vectorI = null,
                epsilonPrecision = null,
                gaussMethodPivotingStrategies = null,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onDimensionUpdated = {
                    systemSolvingMethodViewModel.updateDimension(it)
                },
                onMatrixADataChanged = {
                    systemSolvingMethodViewModel.updateMatrixA(it)
                },
                onVectorBDataChanged = {
                    systemSolvingMethodViewModel.updateVectorB(it)

                },
                onVectorIDataChanged = null,
                onEpsilonPrecisionUpdated = null,
                onGaussMethodPivotingStrategySelected = null,
                onShouldFormFullSolutionChanged = {
                    systemSolvingMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    systemSolvingMethodViewModel.solveSystem(
                        methodType = SystemSolvingMethodViewModel.MethodType.GAUSS_CLASSIC_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                }
            )
        }
        composable(route = SystemSolvingMethodsScreenRoutes.GaussPivotingMethod.routeName) {
            val uiState by systemSolvingMethodViewModel.uiState.collectAsState()
            SystemSolvingMethodInputScreen(
                dimension = uiState.dimension,
                matrixA = uiState.matrixA,
                vectorB = uiState.vectorB,
                vectorI = null,
                epsilonPrecision = null,
                gaussMethodPivotingStrategies = systemSolvingMethodViewModel.gaussMethodPivotingStrategiesList(),
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onDimensionUpdated = {
                    systemSolvingMethodViewModel.updateDimension(it)
                },
                onMatrixADataChanged = {
                    systemSolvingMethodViewModel.updateMatrixA(it)
                },
                onVectorBDataChanged = {
                    systemSolvingMethodViewModel.updateVectorB(it)

                },
                onVectorIDataChanged = null,
                onEpsilonPrecisionUpdated = null,
                onGaussMethodPivotingStrategySelected = {
                    systemSolvingMethodViewModel.updateGaussMethodPivotingStrategy(it)
                },
                onShouldFormFullSolutionChanged = {
                    systemSolvingMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    systemSolvingMethodViewModel.solveSystem(
                        methodType = SystemSolvingMethodViewModel.MethodType.GAUSS_PIVOTING_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                }
            )
        }
        composable(route = SystemSolvingMethodsScreenRoutes.SeidelMethod.routeName) {
            val uiState by systemSolvingMethodViewModel.uiState.collectAsState()
            SystemSolvingMethodInputScreen(
                dimension = uiState.dimension,
                matrixA = uiState.matrixA,
                vectorB = uiState.vectorB,
                vectorI = uiState.vectorI,
                epsilonPrecision = uiState.epsilonPrecision,
                gaussMethodPivotingStrategies = null,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onDimensionUpdated = {
                    systemSolvingMethodViewModel.updateDimension(it)
                },
                onMatrixADataChanged = {
                    systemSolvingMethodViewModel.updateMatrixA(it)
                },
                onVectorBDataChanged = {
                    systemSolvingMethodViewModel.updateVectorB(it)

                },
                onVectorIDataChanged = {
                    systemSolvingMethodViewModel.updateVectorI(it)
                },
                onEpsilonPrecisionUpdated = {
                    systemSolvingMethodViewModel.updateEpsilonPrecision(it)
                },
                onGaussMethodPivotingStrategySelected = null,
                onShouldFormFullSolutionChanged = {
                    systemSolvingMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    systemSolvingMethodViewModel.solveSystem(
                        methodType = SystemSolvingMethodViewModel.MethodType.SEIDEL_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                }
            )
        }
        composable(route = SystemSolvingMethodsScreenRoutes.JacobiMethod.routeName) {
            val uiState by systemSolvingMethodViewModel.uiState.collectAsState()
            SystemSolvingMethodInputScreen(
                dimension = uiState.dimension,
                matrixA = uiState.matrixA,
                vectorB = uiState.vectorB,
                vectorI = uiState.vectorI,
                epsilonPrecision = uiState.epsilonPrecision,
                gaussMethodPivotingStrategies = null,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onDimensionUpdated = {
                    systemSolvingMethodViewModel.updateDimension(it)
                },
                onMatrixADataChanged = {
                    systemSolvingMethodViewModel.updateMatrixA(it)
                },
                onVectorBDataChanged = {
                    systemSolvingMethodViewModel.updateVectorB(it)

                },
                onVectorIDataChanged = {
                    systemSolvingMethodViewModel.updateVectorI(it)
                },
                onEpsilonPrecisionUpdated = {
                    systemSolvingMethodViewModel.updateEpsilonPrecision(it)
                },
                onGaussMethodPivotingStrategySelected = null,
                onShouldFormFullSolutionChanged = {
                    systemSolvingMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    systemSolvingMethodViewModel.solveSystem(
                        methodType = SystemSolvingMethodViewModel.MethodType.JACOBI_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                }
            )
        }
        composable(route = SystemSolvingMethodsScreenRoutes.ThomasMethod.routeName) {
            val uiState by systemSolvingMethodViewModel.uiState.collectAsState()
            SystemSolvingMethodInputScreen(
                dimension = uiState.dimension,
                matrixA = uiState.matrixA,
                vectorB = uiState.vectorB,
                vectorI = null,
                epsilonPrecision = null,
                gaussMethodPivotingStrategies = null,
                shouldFormFullSolution = uiState.shouldFormFullSolution,
                onDimensionUpdated = {
                    systemSolvingMethodViewModel.updateDimension(it)
                },
                onMatrixADataChanged = {
                    systemSolvingMethodViewModel.updateMatrixA(it)
                },
                onVectorBDataChanged = {
                    systemSolvingMethodViewModel.updateVectorB(it)

                },
                onVectorIDataChanged = null,
                onEpsilonPrecisionUpdated = null,
                onGaussMethodPivotingStrategySelected = null,
                onShouldFormFullSolutionChanged = {
                    systemSolvingMethodViewModel.updateShouldFormFullSolution(it)
                },
                onSolveButtonClick = {
                    println("uiState = $uiState")
                    systemSolvingMethodViewModel.solveSystem(
                        methodType = SystemSolvingMethodViewModel.MethodType.THOMAS_METHOD,
                        onSuccess = {
                            navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                            solutionViewModel.updateSolution(it)
                        }
                    )
                }
            )
        }
    }
}
