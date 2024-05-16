package com.vivekchoudhary.kmp.picsplash.data.repository

import io.ktor.client.call.body
import com.vivekchoudhary.kmp.picsplash.data.network.ApiService
import com.vivekchoudhary.kmp.picsplash.data.network.responses.SearchPhotosResponse
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Profile
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic

class PhotoRepositoryImpl(
    private val apiService: ApiService,
    private val cachedData: ICacheData
) : PhotosRepository {
    override suspend fun getPhotos(): List<Photo> {
        return apiService.getPhotos().body()
    }

    override suspend fun searchPhotos(query: String): SearchPhotosResponse {
        return apiService.searchPhotos(query).body()
    }

    override suspend fun getTopics(): List<Topic> {
        return apiService.getTopics().body()
    }

    override suspend fun getCollections(): List<Collection> {
        return apiService.getCollections().body()
    }

    override suspend fun getSavedPhotos(): List<Photo> {
        return apiService.getSavedPhotos().body()
    }

    override suspend fun getUserProfile(): Profile {
        return apiService.getUserProfile().body()
    }

    override suspend fun getTopic(id: String): List<Photo> {
        return apiService.getTopic(id).body()
    }

}

