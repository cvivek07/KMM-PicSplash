import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vivekchoudhary.kmp.picsplash.presentation.screens.App

// Currently, koin does not support compose desktop and web. desktop and web app will not work in this project.
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pinterest",
    ) {
        App()
    }
}