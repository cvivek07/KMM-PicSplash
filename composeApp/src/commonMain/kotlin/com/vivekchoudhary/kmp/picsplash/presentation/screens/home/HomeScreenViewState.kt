package com.vivekchoudhary.kmp.picsplash.presentation.screens.home

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo

sealed interface HomeScreenViewState {
    data object Loading : HomeScreenViewState
    data class Success(
        val photos: List<Photo>,
    ) : HomeScreenViewState

    data class Failure(val error: String) : HomeScreenViewState
}