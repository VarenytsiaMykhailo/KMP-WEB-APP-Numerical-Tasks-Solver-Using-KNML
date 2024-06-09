package presentation.ui.screen

import androidx.compose.runtime.Composable
import kotlinx.browser.document

@Composable
actual fun SolutionScreen(markdownLatexText: String) {
    document.write(
        "<button type=\"button\" onclick=\"window.location = '/';\">Назад</button>" +
                "<br>" +
                "<textarea>" +
                markdownLatexText +
                "</textarea>"
    )
    renderSolutionPage()
}

private fun renderSolutionPage() {
    js("texme.renderPage();")
}