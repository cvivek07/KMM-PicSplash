package com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo

sealed interface SavedPhotosScreenViewState {
    data object Loading : SavedPhotosScreenViewState
    data class Success(
        val photos: List<Photo> = emptyList()
    ) : SavedPhotosScreenViewState

    data class Failure(val error: String) : SavedPhotosScreenViewState
}