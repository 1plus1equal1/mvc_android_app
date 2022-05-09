package dev.hnxtay.android_tutorial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author huypham
 */
@Parcelize
@Serializable
data class User(
    @SerialName("twitter_username") val twitterUsername: String? = "",
    @SerialName("instagram_username") val instagramUsername: String? = "",
    @SerialName("portfolio_url") val portfolioUrl: String? = "",
    @SerialName("bio") val bio: String? = ""
) : Parcelable
