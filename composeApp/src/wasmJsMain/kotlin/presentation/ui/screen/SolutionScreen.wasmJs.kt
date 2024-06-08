package presentation.ui.screen

import androidx.compose.runtime.Composable
import kotlinx.browser.document

@Composable
actual fun SolutionScreen(markdownLatexText: String) {
    document.write(markdownLatexText)
    renderSolutionPage()
}

private fun renderSolutionPage() {
    js("texme.renderPage();")
}