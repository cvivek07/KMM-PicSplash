package com.vivekchoudhary.kmp.picsplash.presentation.screens.topics

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo

sealed interface TopicScreenViewState {
    data object Loading : TopicScreenViewState
    data class Success(
        val photos: List<Photo> = emptyList(),
    ) : TopicScreenViewState

    data class Failure(val error: String) : TopicScreenViewState
}