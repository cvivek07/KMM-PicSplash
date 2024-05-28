package com.vivekchoudhary.kmp.picsplash.presentation.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading
import kmm_picsplash.composeapp.generated.resources.Res
import kmm_picsplash.composeapp.generated.resources.Roboto_Italic
import kmm_picsplash.composeapp.generated.resources.Roboto_Regular
import org.jetbrains.compose.resources.Font
import org.koin.compose.koinInject

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = koinInject(),
    photo: Photo,
    navController: NavController
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                tint = Color.DarkGray,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Text(
                text = photo.user.name,
                style = TextStyle(fontSize = 28.sp, fontFamily = FontFamily(Font(
                    Res.font.Roboto_Regular))),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }

        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
               .data(photo.urls.fullSizeUrl)
                .crossfade(true)
                .build(),
            loading = {
                Loading()
            },
            contentDescription = null,
            modifier = Modifier.wrapContentHeight()
                .wrapContentWidth()
                .clip(RoundedCornerShape(24.dp))
        )
        Text(
            text = photo.altDescription ?: "",
            maxLines = 2,
            fontFamily = FontFamily(Font(Res.font.Roboto_Italic)),
            fontSize = 16.sp
        )
    }

}
