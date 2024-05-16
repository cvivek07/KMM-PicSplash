package com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetAllCollectionsUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetTopicsUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.SearchPhotosUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchPhotosScreenViewModel(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getTopicsUseCase: GetTopicsUseCase,
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase
) : ScreenModel {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext = job + Dispatchers.IO
    private val viewModelScope = CoroutineScope(coroutineContext)

    val viewState = mutableStateOf<SearchScreenViewState>(SearchScreenViewState.Loading)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _photos = MutableStateFlow<List<Photo>>(emptyList())

    init {
        fetchTopicsAndCollections()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    @OptIn(FlowPreview::class)
    val search = searchText
        .debounce(1000)
        .onEach { _isSearching.update { true } }
        .combine(_photos) { text, photos ->
            if (text.isNotBlank()) {
                searchPhotosUseCase.invoke(text)
            } else {
                photos
            }
        }.onEach { _isSearching.update { false } }
        .stateIn(
            screenModelScope,
            SharingStarted.WhileSubscribed(5000),
            _photos.value
        )

    private fun fetchTopicsAndCollections() {
        viewModelScope.launch {
            try {
                val topicsDeferred = async { getTopicsUseCase.invoke() }
                val collectionsDeferred = async { getAllCollectionsUseCase.invoke() }

                val topics = topicsDeferred.await()
                val collections = collectionsDeferred.await()

                viewState.value = SearchScreenViewState.Success(
                    topics.getOrDefault(emptyList()), collections.getOrDefault(
                        emptyList()
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.value = SearchScreenViewState.Failure(e.message.toString())
            }
        }
    }
}