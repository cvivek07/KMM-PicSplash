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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class WebViewScreen(private val url: String) : Screen {
    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        Column(modifier = Modifier.fillMaxSize()) {

            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                tint = Color.DarkGray,
                contentDescription = null,
                modifier = Modifier.clickable {
                    navigator.pop()
                }.padding(20.dp)
            )
            WebView(modifier = Modifier.fillMaxSize(), url)
        }
    }
}