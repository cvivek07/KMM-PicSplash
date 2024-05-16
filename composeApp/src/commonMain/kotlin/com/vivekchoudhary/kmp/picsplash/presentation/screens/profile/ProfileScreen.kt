package com.vivekchoudhary.kmp.picsplash.presentation.screens.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Profile
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Failure
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ProfileViewModel>()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(
        viewModel: ProfileViewModel,
        navigator: Navigator = LocalNavigator.currentOrThrow
    ) {
        val state = viewModel.viewState.value
        when (state) {
            is ProfileScreenViewState.Loading -> Loading()
            is ProfileScreenViewState.Failure -> Failure(state.error)
            is ProfileScreenViewState.Success -> ProfileUI(state.profile)
        }

    }

    @Composable
    private fun ProfileUI(profile: Profile) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = profile.profileImage.largeProfileImage,
                modifier = Modifier.padding(vertical = 40.dp).clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape).size(140.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Text(
                text = profile.name,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(all = 12.dp)
            )


            Text(
                text = profile.bio,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                modifier = Modifier.padding(all = 12.dp)
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    metaDataValue(profile.totalLikes.toString())
                    metaDataLabel("Likes")
                }
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    metaDataValue(profile.followersCount.toString())
                    metaDataLabel("Followers")
                }
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    metaDataValue(profile.followingCount.toString())
                    metaDataLabel("Following")
                }
            }

            Spacer(Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                ItemRow({ ItemLabel("Instagram") }, { ItemValue(profile.instagramUsername) })
                DividerLine()
                ItemRow({ ItemLabel("Twitter") }, { ItemValue(profile.twitterUsername) })
                DividerLine()
                ItemRow({ ItemLabel("Location") }, { ItemValue(profile.location) })
                DividerLine()
                ItemRow({ ItemLabel("Downloads") }, { ItemValue(profile.downloads.toString()) })
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor =  Color.White),
                onClick = {},
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Log Out")
            }
        }
    }

    @Composable
    private fun DividerLine() {
        Divider(color = Color.LightGray.copy(alpha = 0.4f))
    }

    @Composable
    private fun metaDataValue(text: String) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }

    @Composable
    private fun metaDataLabel(text: String) {
        Text(
            text = text,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }

    @Composable
    private fun ItemLabel(text: String) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal)
        )
    }

    @Composable
    private fun ItemValue(text: String) {
        Text(
            text = text,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
        )
    }

    @Composable
    private fun ItemRow(item1: @Composable () -> Unit, item2: @Composable () -> Unit) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
            item1()
            item2()
        }
    }

}