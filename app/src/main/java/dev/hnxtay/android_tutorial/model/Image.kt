package dev.hnxtay.android_tutorial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author huypham on 5/6/2022
 */
@Parcelize
@Serializable
data class Image(
    val id: String,
    val description: String?,
    val urls: UrlResponse,
    val likes: Int,
    val user: User
) : Parcelable
