package com.vivekchoudhary.kmp.picsplash.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerialName("description")
    val description: String? = ""
)