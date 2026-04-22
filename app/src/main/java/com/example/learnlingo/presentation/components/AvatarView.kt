package com.example.learnlingo.presentation.components

import android.net.Uri
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
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.core.net.toUri
import com.example.learnlingo.R

@Composable
fun AvatarView(size: Dp, borderSize: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = borderSize,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF4761F0), Color(0xFFD2CCFF), Color(0xFFCF8CFF)
                    )
                ),
                shape = CircleShape
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
    @RawRes videoResId: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val uri = "android.resource://${context.packageName}/$videoResId".toUri()
            setMediaItem(MediaItem.fromUri(uri))

            // Loop infinitely and autoplay
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true

            prepare()
        }
    }

    // Release the player when this Composable is removed from the UI tree
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Embed the classic Android View inside Compose
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                useController = false // Hides play/pause/timeline controls

                // This acts exactly like ContentScale.Crop for video
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                // Optional: Prevents a black flash before the video starts
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
            }
        }
    )
}