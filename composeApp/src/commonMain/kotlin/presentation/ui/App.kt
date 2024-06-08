package presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.app_name
import numericaltaskssolver.composeapp.generated.resources.back_button
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.navigation.addRootScreenRoutes
import presentation.viewmodel.QueryMethodViewModel
import presentation.viewmodel.SolutionViewModel


@Composable
fun App(
    //systemSolvingMethodViewModel: SystemSolvingMethodViewModel = viewModel(),
    //integralMethodViewModel: IntegralMethodViewModel = viewModel(),
    queryMethodViewModel: QueryMethodViewModel = viewModel { QueryMethodViewModel() },
    solutionViewModel: SolutionViewModel = viewModel { SolutionViewModel() },
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val rootScreen = "RootScreen"
        NavHost(
            navController = navController,
            startDestination = rootScreen,
            modifier = Modifier.padding(innerPadding)
        ) {

            addRootScreenRoutes(
                //systemSolvingMethodViewModel = systemSolvingMethodViewModel,
                //integralMethodViewModel = integralMethodViewModel,
                queryMethodViewModel = queryMethodViewModel,
                solutionViewModel = solutionViewModel,
                navController = navController,
                rootScreen = rootScreen,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AppPreview() {
    MaterialTheme {
        App()
    }
}
