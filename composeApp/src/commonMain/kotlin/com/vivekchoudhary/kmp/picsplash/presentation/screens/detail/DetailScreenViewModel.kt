package com.vivekchoudhary.kmp.picsplash.presentation.screens.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.HomeScreenViewState
import kotlin.coroutines.CoroutineContext

class DetailScreenViewModel: ViewModel() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val newsViewState = mutableStateOf<HomeScreenViewState>(HomeScreenViewState.Loading)

    init {
        viewModelScope.launch {
            try {
//                val photos = allPhotosUseCase.invoke()
//                newsViewState.value = HomeScreenViewState.Success(photos = photos)
            } catch (e: Exception) {
                e.printStackTrace()
                newsViewState.value = HomeScreenViewState.Failure(e.message.toString())
            }
        }
    }
}