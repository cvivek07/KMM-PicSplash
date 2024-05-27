package com.vivekchoudhary.kmp.picsplash.presentation.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(private val getUserProfileUseCase: GetUserProfileUseCase) : ViewModel() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    init {
        getUserProfile()
    }

    val viewState = mutableStateOf<ProfileScreenViewState>(ProfileScreenViewState.Loading)

    private fun getUserProfile() {
        viewModelScope.launch {
            getUserProfileUseCase().onSuccess {
                viewState.value = ProfileScreenViewState.Success(it)
            }.onFailure {
                viewState.value = ProfileScreenViewState.Failure(it.message.toString())
            }
        }
    }

}