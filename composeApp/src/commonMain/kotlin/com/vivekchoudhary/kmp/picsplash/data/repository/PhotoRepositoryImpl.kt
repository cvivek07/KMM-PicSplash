package com.vivekchoudhary.kmp.picsplash.data.repository

import com.vivekchoudhary.kmp.picsplash.data.local.LocalDataSource
import io.ktor.client.call.body
import com.vivekchoudhary.kmp.picsplash.data.network.ApiService
import com.vivekchoudhary.kmp.picsplash.data.network.responses.SearchPhotosResponse
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Profile
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic
import database.toCollectionDomainList
import database.toEntity
import database.toPhotoDomainList
import database.toTopicDomainList

class PhotoRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: LocalDataSource
) : PhotosRepository {

    override suspend fun getPhotos(refresh: Boolean): List<Photo> {
        val result = ArrayList<Photo>()
        if(refresh) {
            apiFetch(result)
        } else {
            if(isPhotosCacheEmpty()) {
                apiFetch(result)
            } else {
                return cachedPhotosData()
            }
        }
        return result
    }

    private suspend fun cachedPhotosData() = localDataSource.getAllPhotos().toPhotoDomainList()

    private suspend fun apiFetch(result: ArrayList<Photo>) {
        var list: ArrayList<Photo>
        for (i in 1..20) {
            list = apiService.getPhotos(i.toString()).body()
            result.addAll(list)
        }
        result.forEach {
            localDataSource.upsertPhoto(it.toEntity())
        }
    }

    private suspend fun isPhotosCacheEmpty() = localDataSource.getAllPhotos().isEmpty()

    override suspend fun searchPhotos(query: String): SearchPhotosResponse {
        return apiService.searchPhotos(query).body()
    }

    override suspend fun getTopics(): List<Topic> {
        return if(localDataSource.getAllTopics().isEmpty()) {
            val topics : List<Topic> = apiService.getTopics().body()
            topics.forEach {
                localDataSource.upsertTopic(it.toEntity())
            }
            localDataSource.getAllTopics().toTopicDomainList()
        } else {
            localDataSource.getAllTopics().toTopicDomainList()
        }
    }

    override suspend fun getCollections(): List<Collection> {
        return if(localDataSource.getAllCollections().isEmpty()) {
            val collections : List<Collection> = apiService.getCollections().body()
            collections.forEach {
                localDataSource.upsertCollection(it.toEntity())
            }
            localDataSource.getAllCollections().toCollectionDomainList()
        } else {
            localDataSource.getAllCollections().toCollectionDomainList()
        }
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

