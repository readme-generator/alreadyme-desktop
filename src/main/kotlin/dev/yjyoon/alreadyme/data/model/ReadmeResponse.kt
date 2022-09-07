package dev.yjyoon.alreadyme.data.model

import dev.yjyoon.alreadyme.ui.model.Readme
import kotlinx.serialization.Serializable

@Serializable
data class ReadmeResponse(
    val id: Long,
    val githubOriginalUrl: String,
    val readmeText: String,
    val createdTime: String
)

fun ReadmeResponse.toReadme() = Readme(
    id = id,
    rawText = readmeText
)
