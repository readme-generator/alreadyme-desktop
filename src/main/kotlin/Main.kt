import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.yjyoon.alreadyme.App
import dev.yjyoon.alreadyme.ui.component.AlreadymeControlPanel
import dev.yjyoon.alreadyme.ui.value.R

fun main() = application {
    val windowState = rememberWindowState(
        position = WindowPosition(Alignment.Center),
        width = 1280.dp,
        height = 768.dp
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = R.string.APP_TITLE,
        undecorated = true,
        state = windowState
    ) {
        App(
            controlPanel = {
                WindowDraggableArea(content = {
                    AlreadymeControlPanel(
                        windowState = windowState,
                        onClose = ::exitApplication
                    )
                })
            }
        )
    }
}
