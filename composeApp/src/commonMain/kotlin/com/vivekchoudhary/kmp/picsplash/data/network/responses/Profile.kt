package com.vivekchoudhary.kmp.picsplash.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("instagram_username")
    val instagramUsername: String,
    @SerialName("twitter_username")
    val twitterUsername: String,
    @SerialName("bio")
    val bio: String,
    @SerialName("location")
    val location: String,
    @SerialName("total_likes")
    val totalLikes: Int,
    @SerialName("followers_count")
    val followersCount: Int,
    @SerialName("following_count")
    val followingCount: Int,
    @SerialName("downloads")
    val downloads: Int,
    @SerialName("profile_image")
    val profileImage: ProfileImage,
)