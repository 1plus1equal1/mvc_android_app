package dev.hnxtay.android_tutorial.model

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
