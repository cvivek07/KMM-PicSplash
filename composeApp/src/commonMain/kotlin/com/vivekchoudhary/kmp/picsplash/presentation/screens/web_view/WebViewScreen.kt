package com.vivekchoudhary.kmp.picsplash.presentation.screens.web_view

import WebView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WebViewScreen(url: String, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            tint = Color.DarkGray,
            contentDescription = null,
            modifier = Modifier.clickable {
                navController.popBackStack()
            }.padding(20.dp)
        )
        WebView(modifier = Modifier.fillMaxSize(), url)
    }
}