package com.slavenski.app.androidstudent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.slavenski.app.androidstudent.ui.theme.AndroidStudentTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStudentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                                .padding(top = 100.dp)
                        ) {
                            ExoPlayerScreen(videoUrl = VIDEO_FOR_EXO_PLAYER)
                        }

                        ServiceButton()
                    }
                }
            }
        }
    }

    override fun onPause() {
        mainViewModel.onStopService()
        super.onPause()
    }

    @Composable
    private fun ExoPlayerScreen(videoUrl: String) {
        val context = LocalContext.current
        val exoPlayer = ExoPlayer
            .Builder(context)
            .build()

        LaunchedEffect(videoUrl) {
            exoPlayer.setMediaItem(MediaItem.fromUri(videoUrl))
            exoPlayer.prepare()
        }

        DisposableEffect(key1 = Unit) {
            onDispose {
                exoPlayer.release()
            }
        }

        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }

    @Composable
    private fun ServiceButton() {
        Button(onClick = {
            mainViewModel.onStartService()
        }) {
            Text(text = "Start Service")
        }
    }
}