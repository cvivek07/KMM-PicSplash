package com.vivekchoudhary.kmp.picsplash.presentation.screens.profile

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Profile

sealed interface ProfileScreenViewState {
    data object Loading : ProfileScreenViewState
    data class Success(
        val profile: Profile,
    ) : ProfileScreenViewState

    data class Failure(val error: String) : ProfileScreenViewState
}