package com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic

sealed interface SearchScreenViewState {
    data object Loading : SearchScreenViewState
    data class Success(
        val topics: List<Topic> = emptyList(),
        val collections: List<Collection> = emptyList()
    ) : SearchScreenViewState

    data class Failure(val error: String) : SearchScreenViewState
}