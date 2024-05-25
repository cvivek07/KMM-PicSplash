package com.vivekchoudhary.kmp.picsplash.data.network


sealed class ApiDefinition {
    sealed class ApiEndpoint(val path: String) {
        data object GetPhotos : ApiEndpoint(path = "/photos")
        data object SearchPhotos : ApiEndpoint(path = "/search/photos")
        data object GetTopics : ApiEndpoint(path = "/topics")
        data class GetTopic(val id: String) : ApiEndpoint(path = "/topics/$id/photos")
        data object GetCollections : ApiEndpoint(path = "/collections")
        data object GetSavedPhotos : ApiEndpoint(path = "/users/fl__q/photos")
        data object GetUserProfile : ApiEndpoint(path = "/users/fl__q")

    }

    object ApiField {
        const val PARAM_QUERY = "query"
        const val PARAM_PAGE = "page"
    }
}