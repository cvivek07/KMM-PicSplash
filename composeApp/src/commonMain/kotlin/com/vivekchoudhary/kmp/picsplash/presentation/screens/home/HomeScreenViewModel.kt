package com.vivekchoudhary.kmp.picsplash.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetAllPhotosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel(
    private val allPhotosUseCase: GetAllPhotosUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val newsViewState = mutableStateOf<HomeScreenViewState>(HomeScreenViewState.Loading)

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            allPhotosUseCase.invoke(false).onSuccess {
                newsViewState.value = HomeScreenViewState.Success(photos = it)
            }.onFailure {
                newsViewState.value = HomeScreenViewState.Failure(it.message.toString())
            }
        }
    }
}