package com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil3.compose.AsyncImage
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import com.vivekchoudhary.kmp.picsplash.presentation.screens.web_view.WebViewScreen

val maxHeight = 1000.dp

class SavedPhotosScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<SavedPhotosScreenViewModel>()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(
        viewModel: SavedPhotosScreenViewModel,
        navigator: Navigator = LocalNavigator.currentOrThrow
    ) {
        val state = viewModel.viewState
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when (val resultedState = state.value) {
                is SavedPhotosScreenViewState.Failure -> Failure(resultedState.error)
                is SavedPhotosScreenViewState.Loading -> Loading()
                is SavedPhotosScreenViewState.Success -> {
                    Text(
                        text = "Saved Photos",
                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(all = 12.dp)
                    )
                    SavedPhotosList(resultedState.photos, onItemClick = {
                        navigator.push(DetailScreen(it))
                    })
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }

    @Composable
    fun SavedPhotosList(photos: List<Photo>, onItemClick: (Photo) -> Unit) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.height(height = com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.maxHeight)
        ) {
            items(items = photos) { photo ->
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp).clickable {
                        onItemClick(photo)
                    }
                ) {
                    AsyncImage(
                        model = photo.urls.regularSizeUrl,
                        contentDescription = null,
                        modifier = Modifier.wrapContentHeight()
                            .wrapContentWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(24.dp))
                    )
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(text = photo.altDescription?: "", maxLines = 2, fontSize = 12.sp, modifier = Modifier.weight(1f, fill = false))
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            tint = Color.DarkGray,
                            contentDescription = null
                        )
                        Text(text = photo.likesCount.toString(), fontSize = 12.sp)
                    }
                }
            }

        }
    }

    @Composable
    fun Failure(message: String) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = message, modifier = Modifier.align(Alignment.Center))
        }
    }

    private fun navigateToWebViewScreen(webUrl: String, navigator: Navigator) {
        navigator.push(WebViewScreen(webUrl))
    }
}