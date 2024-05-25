package com.vivekchoudhary.kmp.picsplash.data.repository

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Profile
import com.vivekchoudhary.kmp.picsplash.data.network.responses.SearchPhotosResponse
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic

interface PhotosRepository {
    suspend fun getPhotos(refresh: Boolean): List<Photo>
    suspend fun searchPhotos(query: String) : SearchPhotosResponse
    suspend fun getTopics() : List<Topic>
    suspend fun getTopic(id: String) : List<Photo>
    suspend fun getCollections() : List<Collection>
    suspend fun getSavedPhotos() : List<Photo>
    suspend fun getUserProfile() : Profile
}