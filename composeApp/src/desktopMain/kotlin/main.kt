import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import presentation.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "NumericalTasksSolver",
    ) {
        App()
    }
}