package dev.yjyoon.alreadyme.data.util

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.writeBytes

object FileUtil {

    private val DEFAULT_FILENAME = "README.md"
    private val DEFAULT_DIRECTORY = System.getProperty("user.home") + "\\Downloads"
    private val FILEDIALOG_TITLE = "Save README.md"

    private val window = ComposeWindow()

    fun saveFile(dirName: String, fileName: String, byteArray: ByteArray) {
        Paths.get(dirName).createDirectories()
        val file = Paths.get(dirName, fileName)

        if (!Files.exists(file)) {
            file.createFile()
        }

        file.writeBytes(byteArray)
    }

    fun openFileDialog(callback: (dirName: String, fileName: String) -> Unit) {
        val fileDialog = object : FileDialog(window, FILEDIALOG_TITLE, SAVE) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    callback(directory, file)
                }
            }
        }.apply {
            file = DEFAULT_FILENAME
            directory = DEFAULT_DIRECTORY

            // filename filtering for linux
            setFilenameFilter { _, name -> name.endsWith(".md") }
        }
        fileDialog.isVisible = true
    }
}
