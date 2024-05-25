package com.vivekchoudhary.kmp.picsplash.data.network

import io.ktor.client.statement.HttpResponse

interface ApiService {
    suspend fun getPhotos(page: String) : HttpResponse
    suspend fun searchPhotos(query: String) : HttpResponse
    suspend fun getTopics() : HttpResponse
    suspend fun getCollections() : HttpResponse
    suspend fun getSavedPhotos() : HttpResponse
    suspend fun getUserProfile() : HttpResponse
    suspend fun getTopic(id: String) : HttpResponse
}