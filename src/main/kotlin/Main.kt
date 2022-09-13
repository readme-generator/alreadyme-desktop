import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import dev.yjyoon.alreadyme.App
import dev.yjyoon.alreadyme.ui.component.AlreadymeControlPanel
import dev.yjyoon.alreadyme.ui.value.R
import java.awt.Toolkit

fun main() = application {
    val screenSize = Toolkit.getDefaultToolkit().screenSize
    val isMaximize = screenSize.width <= 1920

    val windowState = rememberWindowState(
        placement = if(isMaximize) WindowPlacement.Maximized else WindowPlacement.Floating,
        position = WindowPosition(Alignment.Center),
        width = 1280.dp,
        height = 768.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = R.string.APP_TITLE,
        icon = painterResource("drawables/img_logo.png"),
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
