package presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun SolutionScreen(markdownLatexText: String) {
    // TODO find lib, that can render latex and markdown on JWM Desktop
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = markdownLatexText,
        )
    }
}