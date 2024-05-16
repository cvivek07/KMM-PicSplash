package com.vivekchoudhary.kmp.picsplash.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetPhotos
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.SearchPhotos
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetTopics
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetTopic
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetCollections
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetSavedPhotos
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiEndpoint.GetUserProfile
import com.vivekchoudhary.kmp.picsplash.data.network.ApiDefinition.ApiField.PARAM_QUERY

class ApiServiceImpl(private val httpClient: HttpClient) : ApiService {

    override suspend fun getPhotos(): HttpResponse {
        return httpClient.get(GetPhotos.path)
    }

    override suspend fun searchPhotos(query: String): HttpResponse {
        return httpClient.get(SearchPhotos.path) {
            parameter(PARAM_QUERY, query)
        }
    }

    override suspend fun getTopics(): HttpResponse {
        return httpClient.get(GetTopics.path)
    }

    override suspend fun getCollections(): HttpResponse {
        return httpClient.get(GetCollections.path)
    }

    override suspend fun getSavedPhotos(): HttpResponse {
        return httpClient.get(GetSavedPhotos.path)
    }

    override suspend fun getUserProfile(): HttpResponse {
        return httpClient.get(GetUserProfile.path)
    }

    override suspend fun getTopic(id: String): HttpResponse {
        return httpClient.get(GetTopic(id).path)
    }
}