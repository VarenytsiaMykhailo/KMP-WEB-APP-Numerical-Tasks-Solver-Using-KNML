package com.github.varenytsiamykhailo.numericaltaskssolver.presentation.ui.screen.systemsolvingmethods

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import numericaltaskssolver.composeapp.generated.resources.Res
import numericaltaskssolver.composeapp.generated.resources.gauss_pivoting_strategy
import numericaltaskssolver.composeapp.generated.resources.solve_system
import org.jetbrains.compose.resources.stringResource
import presentation.ui.component.DimensionInput
import presentation.ui.component.Dropdown
import presentation.ui.component.EpsilonPrecisionInput
import presentation.ui.component.FormSolutionCheckbox
import presentation.ui.component.MatrixInput
import presentation.ui.component.VectorInput

@Composable
fun SystemSolvingMethodInputScreen(
    dimension: Int,
    matrixA: List<List<Double>>,
    vectorB: List<Double>,
    vectorI: List<Double>?,
    epsilonPrecision: Double?,
    gaussMethodPivotingStrategies: List<String>?,
    shouldFormFullSolution: Boolean,
    onDimensionUpdated: (Int) -> Unit,
    onMatrixADataChanged: (List<List<Double>>) -> Unit,
    onVectorBDataChanged: (List<Double>) -> Unit,
    onVectorIDataChanged: ((List<Double>) -> Unit)?,
    onEpsilonPrecisionUpdated: ((Double?) -> Unit)?,
    onGaussMethodPivotingStrategySelected: ((String) -> Unit)?,
    onShouldFormFullSolutionChanged: (Boolean) -> Unit,
    onSolveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DimensionInput(
                dimension = dimension,
                onDimensionUpdated = onDimensionUpdated
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "AX = B:",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            MatrixInput(
                label = "A = ",
                rows = dimension,
                columns = dimension,
                data = matrixA,
                onDataChanged = onMatrixADataChanged,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            //horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            VectorInput(
                label = "B = ",
                rows = dimension,
                data = vectorB,
                onDataChanged = onVectorBDataChanged,
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (vectorI != null && onVectorIDataChanged != null) {
                VectorInput(
                    label = "I = ",
                    rows = dimension,
                    data = vectorI,
                    onDataChanged = onVectorIDataChanged,
                )
            }
        }
        if (onEpsilonPrecisionUpdated != null) {
            Spacer(modifier = Modifier.height(16.dp))
            EpsilonPrecisionInput(
                epsilonPrecision = epsilonPrecision,
                onEpsilonPrecisionUpdated = onEpsilonPrecisionUpdated,
            )
        }
        if (gaussMethodPivotingStrategies != null && onGaussMethodPivotingStrategySelected != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.gauss_pivoting_strategy),
            )
            Dropdown(
                items = gaussMethodPivotingStrategies,
                onItemSelected = onGaussMethodPivotingStrategySelected
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        FormSolutionCheckbox(
            shouldFormFullSolution = shouldFormFullSolution,
            onFormFullSolutionChanged = onShouldFormFullSolutionChanged,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSolveButtonClick,
            modifier = modifier
                .widthIn(min = 250.dp),
        ) {
            Text(
                text = stringResource(Res.string.solve_system),
            )
        }
    }
}
/*
@Preview(
    showSystemUi = true,
)
@Composable
fun SystemSolvingMethodInputScreenPreview() {
    NumericalTasksSolverTheme {
        SystemSolvingMethodInputScreen(
            dimension = 3,
            matrixA = listOf(
                listOf(1.0, 2.0, 3.0),
                listOf(4.0, 5.0, 6.0),
                listOf(7.0, 8.0, 9.0)
            ),
            vectorB = listOf(1.0, 2.0, 3.0),
            vectorI = listOf(1.0, 2.0, 3.0),
            epsilonPrecision = 0.001,
            gaussMethodPivotingStrategies = null,
            shouldFormFullSolution = false,
            onDimensionUpdated = {},
            onMatrixADataChanged = {},
            onVectorBDataChanged = {},
            onVectorIDataChanged = {},
            onEpsilonPrecisionUpdated = {},
            onGaussMethodPivotingStrategySelected = null,
            onShouldFormFullSolutionChanged = {},
            onSolveButtonClick = {},
        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun SystemSolvingMethodInputScreenWithoutVectorIPreview() {
    NumericalTasksSolverTheme {
        SystemSolvingMethodInputScreen(
            dimension = 3,
            matrixA = listOf(
                listOf(1.0, 2.0, 3.0),
                listOf(4.0, 5.0, 6.0),
                listOf(7.0, 8.0, 9.0)
            ),
            vectorB = listOf(1.0, 2.0, 3.0),
            vectorI = null,
            epsilonPrecision = 0.001,
            gaussMethodPivotingStrategies = listOf("One", "Two", "Three", "Four"),
            shouldFormFullSolution = false,
            onDimensionUpdated = {},
            onMatrixADataChanged = {},
            onVectorBDataChanged = {},
            onVectorIDataChanged = {},
            onEpsilonPrecisionUpdated = {},
            onGaussMethodPivotingStrategySelected = {},
            onShouldFormFullSolutionChanged = {},
            onSolveButtonClick = {},
        )
    }
}

 */