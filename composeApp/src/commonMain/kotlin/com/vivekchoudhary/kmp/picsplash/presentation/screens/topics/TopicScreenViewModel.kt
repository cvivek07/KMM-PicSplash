package com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetTopicByIdUseCase
import com.vivekchoudhary.kmp.picsplash.presentation.screens.topics.TopicScreenViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TopicScreenViewModel(
    private val getTopicByIdUseCase: GetTopicByIdUseCase
) : ScreenModel {

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