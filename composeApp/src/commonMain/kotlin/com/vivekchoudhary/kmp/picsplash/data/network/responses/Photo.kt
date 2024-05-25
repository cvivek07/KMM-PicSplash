package com.vivekchoudhary.kmp.picsplash.data.network.responses

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Stable
@Serializable
data class Photo(
    @SerialName("id")
    val id: String,
    @SerialName("urls")
    val urls: Urls,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
    @SerialName("alt_description")
    val altDescription: String? = "",
    @SerialName("description")
    val description: String? = "",
    @SerialName("likes")
    val likesCount: Int,
    @SerialName("user")
    val user: User
)

@Stable
@Serializable
data class Urls(
    @SerialName("regular")
    val regularSizeUrl: String,
    @SerialName("full")
    val fullSizeUrl: String
)

@Stable
@Serializable
data class User(
    @SerialName("profile_image")
    val profileImage: ProfileImage,
    @SerialName("name")
    val name: String,
    @SerialName("links")
    val links: Links
)

@Stable
@Serializable
data class ProfileImage(
    @SerialName("medium")
    val mediumProfileImage: String,
    @SerialName("large")
    val largeProfileImage: String
)

@Stable
@Serializable
data class Links(
    @SerialName("html")
    val profileLink: String
)

fun Photo.getImageUrl(): String {
    return this.urls.regularSizeUrl
}

fun Photo.getAspectRatio(): Float {
    val width = this.width
    val height = this.height
    if (width > height) {
        return (width / height).toFloat()
    }
    return (height / width).toFloat()
}