package com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Failure
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.PhotosList
import com.vivekchoudhary.kmp.picsplash.presentation.screens.topics.TopicScreenViewState
import com.vivekchoudhary.kmp.picsplash.presentation.screens.web_view.WebViewScreen


class TopicScreen(private val topic: Topic) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<TopicScreenViewModel>()
        viewModel.fetchTopicById(topic.id)
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(
        viewModel: TopicScreenViewModel,
        navigator: Navigator = LocalNavigator.currentOrThrow
    ) {
        when (val state = viewModel.viewState.value) {
            is TopicScreenViewState.Failure -> {
                Failure(state.error)
            }

            is TopicScreenViewState.Loading -> Loading()
            is TopicScreenViewState.Success -> {
                val photos = state.photos
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            tint = Color.DarkGray,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                navigator.pop()
                            }.padding(20.dp)
                        )
                        Text(
                            text = topic.title,
                            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(all = 12.dp)
                        )
                    }
                    PhotosList(photos, onItemClick = {
                        navigator.push(DetailScreen(it))
                    }, onProfileImageClick = {
                        navigateToWebViewScreen(it, navigator)
                    })
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }

    }

    private fun navigateToWebViewScreen(webUrl: String, navigator: Navigator) {
        navigator.push(WebViewScreen(webUrl))
    }
}