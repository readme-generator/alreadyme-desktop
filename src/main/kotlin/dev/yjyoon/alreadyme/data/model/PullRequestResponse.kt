package dev.yjyoon.alreadyme.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PullRequestResponse(
    val pullRequestUrl: String
)
