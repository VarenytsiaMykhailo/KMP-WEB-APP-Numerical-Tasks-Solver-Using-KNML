package presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import presentation.navigation.route.OtherScreenRoutes
import presentation.navigation.route.RootScreenRoutes
import presentation.ui.screen.querymethod.QueryMethodInputScreen
import presentation.ui.screen.SolutionScreen
import presentation.viewmodel.QueryMethodViewModel
import presentation.viewmodel.SolutionViewModel

fun NavGraphBuilder.addOtherScreenRoutes(
    queryMethodViewModel: QueryMethodViewModel,
    solutionViewModel: SolutionViewModel,
    navController: NavHostController,
) {
    composable(route = OtherScreenRoutes.SolutionScreen.routeName) {
        val uiState = solutionViewModel.uiState.collectAsState()
        SolutionScreen(markdownLatexText = uiState.value.solutionString)
    }
    composable(route = RootScreenRoutes.QueryMethod.routeName) {
        val uiState = queryMethodViewModel.uiState.collectAsState()
        QueryMethodInputScreen(
            query = uiState.value.query,
            onQueryChanged = {
                queryMethodViewModel.updateQuery(it)
            },
            onSolveButtonClick = {
                println("qwerty123 - QueryMethodInputScreen - onSolveButtonClick - uiState = $uiState")
                queryMethodViewModel.makeQuery(
                    onSuccess = {
                        navController.navigate(OtherScreenRoutes.SolutionScreen.routeName)
                        solutionViewModel.updateSolution(it)
                    }
                )
            },
        )
    }
}
