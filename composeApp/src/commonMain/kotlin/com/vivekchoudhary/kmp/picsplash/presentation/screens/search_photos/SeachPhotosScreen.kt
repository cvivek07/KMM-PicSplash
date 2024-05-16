package com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic
import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreen
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.PhotosList
import com.vivekchoudhary.kmp.picsplash.presentation.screens.web_view.WebViewScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

val maxHeight = 1000.dp

class SearchPhotosScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<SearchPhotosScreenViewModel>()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: SearchPhotosScreenViewModel) {
        var search = viewModel.searchText.collectAsState().value
        val navigator = LocalNavigator.currentOrThrow
        val photos = viewModel.search.collectAsState().value
        val isSearching = viewModel.isSearching.collectAsState().value
        when (val viewState = viewModel.viewState.value) {
            is SearchScreenViewState.Failure -> {}
            is SearchScreenViewState.Loading -> Loading()
            is SearchScreenViewState.Success -> {
                Box(contentAlignment = Alignment.TopCenter) {
                    Column(
                        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (photos.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(72.dp))
                            PhotosList(photos = photos, onItemClick = {
                                navigator.push(DetailScreen(it))
                            },
                                onProfileImageClick = {
                                    navigateToWebViewScreen(it, navigator)
                                })
                            Spacer(modifier = Modifier.height(72.dp))
                        } else {
                            CollectionsList(viewState.collections.take(5))
                            PopularTopics(viewState.topics, onTopicClicked = {
                                navigator.push(TopicScreen(it))
                            })
                            Spacer(modifier = Modifier.height(72.dp))
                        }
                    }
                    CustomSearchView(search, onValueChange = {
                        search = it
                        viewModel.onSearchTextChange(it)
                    }, onCleared = {
                        search = ""
                        viewModel.onSearchTextChange(search)
                    })
                }
                if (isSearching) {
                    Loading()
                }
            }
        }

    }

    @Composable
    private fun PopularTopics(
        topics: List<Topic>,
        onTopicClicked: (Topic) -> Unit
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Popular on Pinterest", style = TextStyle(
                fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp
            )
        )
        TopicsList(topics, onTopicClicked)
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun CollectionsList(collections: List<Collection>) {
        Column {
            val pageCount = Int.MAX_VALUE
            val realSize = collections.size
            val middlePage = pageCount / 2
            val pagerState =
                rememberPagerState(initialPage = middlePage - (middlePage % realSize), pageCount = {
                    collections.size
                })
            val isDraggedState = pagerState.interactionSource.collectIsDraggedAsState()

            LaunchedEffect(isDraggedState) {
                snapshotFlow { isDraggedState.value }.collectLatest { isDragged ->
                    if (!isDragged) {
                        while (true) {
                            delay(3000L)
                            runCatching {
                                pagerState.animateScrollToPage(pagerState.currentPage.inc() % pagerState.pageCount)
                            }
                        }
                    }
                }
            }
            HorizontalPager(state = pagerState) { index ->
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(model = collections[index].coverPhoto.urls.regularSizeUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(400.dp).wrapContentWidth().drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.LightGray),
                                startY = size.height,
                                endY = size.height
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        })
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = collections[index].title, style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 21.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 2.sp
                            )
                        )
                        Text(
                            text = collections[index].description ?: "", style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 2.sp
                            )
                        )
                    }
                }
            }
            Row(
                Modifier.wrapContentHeight().fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(collections.size) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier.padding(2.dp).clip(CircleShape).background(color)
                            .size(8.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun TopicsList(topics: List<Topic>, onTopicClicked: (Topic) -> Unit) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp).heightIn(max = maxHeight)
        ) {
            items(count = topics.size) { index ->
                Box(
                    modifier = Modifier.padding(2.dp).clickable {
                        onTopicClicked(topics[index])
                    }, contentAlignment = Alignment.Center
                ) {
                    AsyncImage(model = topics[index].coverPhoto.urls.regularSizeUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.wrapContentWidth().height(120.dp)
                            .padding(vertical = 8.dp).clip(RoundedCornerShape(16.dp))
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.LightGray),
                                    startY = size.height,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            })

                    Text(
                        text = topics[index].title, style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            letterSpacing = 2.sp
                        )
                    )
                }

            }
        }
    }


    @Composable
    fun CustomSearchView(
        search: String,
        modifier: Modifier = Modifier,
        onValueChange: (String) -> Unit,
        onCleared: () -> Unit
    ) {
        Box(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp)
                .clip(CircleShape), contentAlignment = Alignment.Center
        ) {
            TextField(
                value = search,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    placeholderColor = Color.Gray.copy(0.9f),
                    leadingIconColor = Color.Gray.copy(0.9f),
                    trailingIconColor = Color.Gray.copy(0.9f),
                    textColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Gray.copy(0.3f),
                    unfocusedLabelColor = Color.Gray.copy(0.3f),
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Gray
                ),
                leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "") },
                trailingIcon = {
                    if (search.isNotEmpty()) Icon(imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable { onCleared() })
                },
                placeholder = { Text(text = "Search Pinterest", color = Color.Gray.copy(0.9f)) },
                modifier = modifier.fillMaxWidth()
            )
        }
    }

    private fun navigateToWebViewScreen(webUrl: String, navigator: Navigator) {
        navigator.push(WebViewScreen(webUrl))
    }
}