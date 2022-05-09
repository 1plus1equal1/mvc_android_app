package dev.hnxtay.android_tutorial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author huypham on 5/6/2022
 */
@Parcelize
@Serializable
data class UrlResponse(
    val raw: String?,
    val full: String?,
    val regular: String?,
    val small: String?,
) : Parcelable
