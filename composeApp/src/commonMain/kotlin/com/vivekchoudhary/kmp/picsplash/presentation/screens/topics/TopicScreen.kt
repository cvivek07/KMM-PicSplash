package com.vivekchoudhary.kmp.picsplash.presentation.screens.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Failure
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.PhotosList
import org.koin.compose.koinInject

@Composable
fun TopicScreen(
    viewModel: TopicScreenViewModel = koinInject(),
    navController: NavController
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
//                            navigator.pop()
                        }.padding(20.dp)
                    )
//                    Text(
//                        text = topic.title,
//                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
//                        modifier = Modifier.padding(all = 12.dp)
//                    )
                }
                PhotosList(photos, onItemClick = {
//                    navigator.push(DetailScreen(it))
                }, onProfileImageClick = {
                    navigateToWebViewScreen(it, navController)
                })
                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }

}

private fun navigateToWebViewScreen(webUrl: String, navController: NavController) {
//    navigator.push(WebViewScreen(webUrl))
}