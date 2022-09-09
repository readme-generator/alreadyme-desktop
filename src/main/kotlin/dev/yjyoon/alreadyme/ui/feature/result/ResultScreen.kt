package dev.yjyoon.alreadyme.ui.feature.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.Markdown
import com.mikepenz.markdown.MarkdownDefaults
import dev.yjyoon.alreadyme.ui.component.AlreadymeDialog
import dev.yjyoon.alreadyme.ui.component.AlreadymeLoading
import dev.yjyoon.alreadyme.ui.model.Readme
import dev.yjyoon.alreadyme.ui.value.MarkdownTypography
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun ResultScreen(
    readme: Readme,
    isLoading: Boolean,
    showDialog: Boolean,
    onCloseDialog: () -> Unit,
    onDownload: (Long) -> Unit,
    onPullRequest: (Long) -> Unit,
    onBackToTitle: () -> Unit
) {
    val scrollState = rememberScrollState()
    var successDialogMessage by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(color = MaterialTheme.colors.onSurface)
                    .padding(vertical = 30.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 192.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = onBackToTitle,
                        contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp),
                        enabled = !isLoading
                    ) {
                        Text(text = R.string.BACK_TO_HOME)
                    }
                    Button(
                        onClick = {
                            successDialogMessage = R.string.DOWNLOAD_COMPLETE
                            onDownload(readme.id)
                        },
                        contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp),
                        enabled = !isLoading
                    ) {
                        Text(text = R.string.DOWNLOAD_DIRECTLY)
                    }
                    Button(
                        onClick = {
                            successDialogMessage = R.string.PR_COMPLETE
                            onPullRequest(readme.id)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = R.color.Green,
                            contentColor = R.color.White
                        ),
                        contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp),
                        enabled = !isLoading
                    ) {
                        Icon(
                            painter = painterResource("drawables/ic_git.svg"),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(text = R.string.PR_TO_REPOSITORY)
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(color = MaterialTheme.colors.onSurface),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = R.string.README_CREATED,
                        color = MaterialTheme.colors.surface,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(32.dp))
                Card(
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 2.dp, color = R.color.DarkGray.copy(alpha = 0.12f)),
                    modifier = Modifier.fillMaxHeight().width(980.dp),
                ) {
                    Markdown(
                        content = """
# Compose Multiplatform, by JetBrains
Compose Kotlin UI framework port for desktop platforms (macOS, Linux, Windows) and Web, components outside of the core Compose repository
at https://android.googlesource.com/platform/frameworks/support.
Preview functionality (check your application UI without building/running it) for desktop platforms is available via IDEA plugin (https://plugins.jetbrains.com/plugin/16541-compose-multiplatform-ide-support).
## Repository organization ##
   * [artwork](artwork) - design artifacts
   * [benchmarks](benchmarks) - collection of benchmarks
   * [compose](compose) - composite build of [Compose-jb sources](https://github.com/JetBrains/androidx)
   * [ci](ci) - Continuous Integration helpers
   * [examples](examples) - examples of multiplatform Compose applications for Desktop, Android and Web
       * [codeviewer](examples/codeviewer) - File Browser and Code Viewer application for Android and Desktop
       * [imageviewer](examples/imageviewer) - Image Viewer application for Android and Desktop
       * [issues](examples/issues) - GitHub issue tracker with an adaptive UI and ktor-client
       * [game](examples/falling-balls) - Simple game
       * [game](experimental/examples/falling-balls-mpp) - Simple game for web target
       * [compose-bird](examples/web-compose-bird) - A flappy bird clone using Compose for Web
       * [notepad](examples/notepad) - Notepad, using the new experimental Composable Window API
       * [todoapp](examples/todoapp) - TODO items tracker with persistence and multiple screens
       * [todoapp-lite](examples/todoapp-lite) - A simplified version of [todoapp](examples/todoapp), fully based on Compose
       * [widgets gallery](examples/widgets-gallery) - Gallery of standard widgets
       * [IDEA plugin](examples/intellij-plugin) - Plugin for IDEA using Compose for Desktop
   * [gradle-plugins](gradle-plugins) - a plugin, simplifying usage of Compose Multiplatform with Gradle
   * [templates](templates) - new application templates
   * [tutorials](tutorials) - tutorials on using Compose Multiplatform
       * [Getting started](tutorials/Getting_Started)
       * [Image and icon manipulations](tutorials/Image_And_Icons_Manipulations)
       * [Mouse events and hover](tutorials/Mouse_Events)
       * [Scrolling and scrollbars](tutorials/Desktop_Components#scrollbars)
       * [Tooltips](tutorials/Desktop_Components#tooltips)
       * [Top level windows management](tutorials/Window_API_new)
       * [Menu, tray, notifications](tutorials/Tray_Notifications_MenuBar_new)
       * [Keyboard support](tutorials/Keyboard)
       * [Tab focus navigation](tutorials/Tab_Navigation)
       * [Building native distribution](tutorials/Native_distributions_and_local_execution)
       * [Signing and notarization](tutorials/Signing_and_notarization_on_macOS)
       * [Swing interoperability](tutorials/Swing_Integration)
       * [Development for Android](tutorials/Development_for_Android)
       * [Navigation](tutorials/Navigation)
       * [Accessebility](https://github.com/JetBrains/compose-jb/tree/master/tutorials/Accessibility)
   * [tutorials: compose for web](tutorials/Web) - tutorials on using Compose for Web
       * [Getting started with Compose for Web](tutorials/Web/Getting_Started) 
       * [Building web UI](tutorials/Web/Building_UI)
       * [Handling Events](tutorials/Web/Events_Handling)
       * [Controlled and Uncontrolled inputs](tutorials/Web/Controlled_Uncontrolled_Inputs)
       * [Style DSL](tutorials/Web/Style_Dsl)
       * [Using test-utils](tutorials/Web/Using_Test_Utils)
   * [components](components) - custom components of Compose Multiplatform
       * [Split Pane](components/SplitPane)
   * [experimental](experimental) - experimental components and examples
       * [cef](experimental/cef) - CEF integration in Jetpack Compose (somewhat outdated)
       * [Video Player](experimental/components/VideoPlayer)
       * [LWJGL integration](experimental/lwjgl-integration) - An example showing how to integrate Compose with [LWJGL](https://www.lwjgl.org)
       * [CLI example](experimental/build_from_cli) - An example showing how to build Compose without Gradle
       
## Getting latest version of Compose Multiplatform ##
See https://github.com/JetBrains/compose-jb/releases/latest for the latest stable release or https://github.com/JetBrains/compose-jb/releases for all stable and dev releases.
            """.trimIndent(),
                        modifier = Modifier.padding(36.dp),
                        typography = MarkdownDefaults.markdownTypography(
                            h1 = MarkdownTypography.h1,
                            h2 = MarkdownTypography.h2,
                            h3 = MarkdownTypography.h3,
                            h4 = MarkdownTypography.h4,
                            h5 = MarkdownTypography.h5,
                            h6 = MarkdownTypography.h6,
                            body1 = MarkdownTypography.body1,
                            body2 = MarkdownTypography.body2
                        )
                    )
                }
                Spacer(Modifier.height(32.dp))
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = R.color.DarkGray.copy(alpha = 0.5f))
                        .clickable(enabled = false, onClick = {}),
                    contentAlignment = Alignment.Center
                ) {
                    AlreadymeLoading(size = 40f)
                }
            }
            if (showDialog) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = R.color.DarkGray.copy(alpha = 0.67f))
                        .clickable(enabled = false, onClick = {}),
                    contentAlignment = Alignment.Center
                ) {
                    AlreadymeDialog(message = successDialogMessage, onClose = onCloseDialog)
                }
            }
        }
    }
}
