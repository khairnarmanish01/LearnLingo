package com.example.learnlingo.presentation.components

import android.view.TextureView
import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.learnlingo.R

@Composable
fun AvatarView(size: Dp, borderSize: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = borderSize, brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4761F0), Color(0xFFD2CCFF), Color(0xFFCF8CFF)
                    )
                ), shape = CircleShape
            )
            .clip(CircleShape)
            .padding(borderSize)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color.White)
                .padding(borderSize)
        ) {
            LoopingVideoPlayer(
                videoResId = R.raw.ai_avatat_vid,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun LoopingVideoPlayer(
    @RawRes videoResId: Int, modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val uri = "android.resource://${context.packageName}/$videoResId".toUri()
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true
            prepare()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> exoPlayer.playWhenReady = true
                Lifecycle.Event.ON_STOP -> exoPlayer.playWhenReady = false
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    AndroidView(modifier = modifier, factory = { ctx ->
        PlayerView(ctx).apply {
            player = exoPlayer
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            (videoSurfaceView as? TextureView)
        }
    }, update = { view ->
    })
}