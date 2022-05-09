package dev.hnxtay.android_tutorial.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Response(
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<Image>
) : Parcelable

@Parcelize
@Serializable
data class Image(
    val id: String,
    val description: String?,
    val urls: Url,
    val likes: Int,
    
) : Parcelable

@Parcelize
@Serializable
data class Url(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
) : Parcelable