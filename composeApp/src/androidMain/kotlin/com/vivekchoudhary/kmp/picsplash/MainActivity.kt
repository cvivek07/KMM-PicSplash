package com.vivekchoudhary.kmp.picsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vivekchoudhary.kmp.picsplash.presentation.screens.PicSplashApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PicSplashApp()
        }
    }
}