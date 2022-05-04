package dev.hnxtay.android_tutorial

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<Image>
)

@Serializable
data class Image(
    val id: String,
    val description: String?,
    val urls: Url
)

@Serializable
data class Url(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
)