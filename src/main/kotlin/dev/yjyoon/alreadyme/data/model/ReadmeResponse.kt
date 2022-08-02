package dev.yjyoon.alreadyme.data.model

import dev.yjyoon.alreadyme.ui.model.Readme
import kotlinx.serialization.Serializable

@Serializable
data class ReadmeResponse(
    val readme: String
)

fun ReadmeResponse.toReadme() = Readme(
    markdown = readme
)
