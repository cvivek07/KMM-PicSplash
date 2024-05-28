package com.vivekchoudhary.kmp.picsplash.presentation.screens.topics

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic
import com.vivekchoudhary.kmp.picsplash.presentation.NavigationUtil
import com.vivekchoudhary.kmp.picsplash.presentation.screens.Screen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Failure
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.PhotosList
import kmm_picsplash.composeapp.generated.resources.Res
import kmm_picsplash.composeapp.generated.resources.Roboto_Regular
import org.jetbrains.compose.resources.Font
import org.koin.compose.koinInject

@Composable
fun TopicScreen(
    topic: Topic,
    viewModel: TopicScreenViewModel = koinInject(),
    navController: NavController
) {
    viewModel.fetchTopicById(topic.id)
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
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        tint = Color.DarkGray,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Text(
                        text = topic.title,
                        style = TextStyle(fontSize = 28.sp, fontFamily = FontFamily(Font(
                            Res.font.Roboto_Regular))),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                PhotosList(photos, onItemClick = {
                    NavigationUtil.photo = it
                    navController.navigate(Screen.Detail.route)
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