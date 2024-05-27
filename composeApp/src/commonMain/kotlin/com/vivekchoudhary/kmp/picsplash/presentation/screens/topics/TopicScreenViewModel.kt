package com.vivekchoudhary.kmp.picsplash.presentation.screens.topics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetTopicByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TopicScreenViewModel(
    private val getTopicByIdUseCase: GetTopicByIdUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val viewState = mutableStateOf<TopicScreenViewState>(TopicScreenViewState.Loading)

    fun fetchTopicById(id: String) {
        viewModelScope.launch {
             getTopicByIdUseCase(id).onSuccess {
                 viewState.value = TopicScreenViewState.Success(it)
             }.onFailure {
                 viewState.value = TopicScreenViewState.Failure(it.message.toString())
             }
        }
    }

}