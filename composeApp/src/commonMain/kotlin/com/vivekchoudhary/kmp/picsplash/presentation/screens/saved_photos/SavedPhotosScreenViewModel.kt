package com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetSavedPhotosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SavedPhotosScreenViewModel(
    private val getSavedPhotosUseCase: GetSavedPhotosUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val viewState = mutableStateOf<SavedPhotosScreenViewState>(SavedPhotosScreenViewState.Loading)

    init {
        getSavedPhotos()
    }

    private fun getSavedPhotos() {
        viewModelScope.launch {
            viewModelScope.launch {
                getSavedPhotosUseCase().onSuccess {
                    viewState.value = SavedPhotosScreenViewState.Success(it)
                }.onFailure {
                    viewState.value = SavedPhotosScreenViewState.Failure(it.message.toString())
                }
            }
        }
    }
}