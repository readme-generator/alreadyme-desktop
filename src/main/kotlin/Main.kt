import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.yjyoon.alreadyme.App
import dev.yjyoon.alreadyme.ui.value.R

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = R.string.APP_TITLE,
        undecorated = false,
        state = WindowState(
            position = WindowPosition(Alignment.Center),
            width = 1280.dp,
            height = 800.dp
        )
    ) {
        App()
    }
}
