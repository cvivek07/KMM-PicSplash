package com.vivekchoudhary.kmp.picsplash.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetAllPhotosUseCase
import kotlin.coroutines.CoroutineContext

class HomeScreenViewModel(
    private val allPhotosUseCase: GetAllPhotosUseCase
) : ScreenModel {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val newsViewState = mutableStateOf<HomeScreenViewState>(HomeScreenViewState.Loading)

    init {
        getPhotos()
    }

    private fun getPhotos() {
        viewModelScope.launch {
            allPhotosUseCase().onSuccess {
                newsViewState.value = HomeScreenViewState.Success(photos = it)
            }.onFailure {
                newsViewState.value = HomeScreenViewState.Failure(it.message.toString())
            }
        }
    }
}