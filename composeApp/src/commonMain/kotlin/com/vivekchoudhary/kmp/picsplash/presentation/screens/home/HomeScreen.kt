package com.vivekchoudhary.kmp.picsplash.presentation.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.maxHeight
import com.vivekchoudhary.kmp.picsplash.presentation.screens.web_view.WebViewScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeScreenViewModel>()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(
        viewModel: HomeScreenViewModel,
        navigator: Navigator = LocalNavigator.currentOrThrow
    ) {
        val state = viewModel.newsViewState
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (val resultedState = state.value) {
                is HomeScreenViewState.Failure -> Failure(resultedState.error)
                is HomeScreenViewState.Loading -> Loading()
                is HomeScreenViewState.Success -> {
                    PhotosList(resultedState.photos, onItemClick = {
                        navigator.push(DetailScreen(it))
                    },
                        onProfileImageClick = {
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

@Composable
fun PhotosList(
    photos: List<Photo>,
    onItemClick: (Photo) -> Unit,
    onProfileImageClick: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.height(height = maxHeight)
    ) {
        items(items = photos) { photo ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.animateContentSize().padding(8.dp)
                    .clickable {
                        onItemClick(photo)
                    }
            ) {

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalPlatformContext.current)
                        .data(photo.urls.regularSizeUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.wrapContentHeight()
                        .wrapContentWidth()
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(24.dp))
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = photo.user.profileImage.mediumProfileImage,
                        modifier = Modifier.clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape).clickable {
                                onProfileImageClick(photo.user.links.profileLink)
                            },
                        contentDescription = null
                    )
                    Text(
                        text = photo.user.name,
                        maxLines = 2,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Spacer(modifier = Modifier.weight(1f, fill = true))
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.rotate(90f)
                    )
                }
            }

        }

    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Black,
        )
    }
}

@Composable
fun Failure(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = message, modifier = Modifier.align(Alignment.Center))
    }
}