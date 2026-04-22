package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.Primary

@Composable
fun AvatarSelectionScreen(onBack: () -> Unit, onConfirm: (avatarRes: Int) -> Unit) {
    val avatars = listOf(
        R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3,
        R.drawable.avatar_4, R.drawable.avatar_5, R.drawable.avatar_6,
        R.drawable.avatar_7, R.drawable.avatar_8, R.drawable.avatar_9,
        R.drawable.avatar_10, R.drawable.avatar_11, R.drawable.avatar_12
    )

    var selectedAvatar by remember { mutableIntStateOf(avatars[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 0.56f,
            chatBubbleText = stringResource(R.string.choose_your_profile_photo),
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(avatars) { avatar ->
                val isSelected = selectedAvatar == avatar
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .clickable { selectedAvatar = avatar }
                        .border(
                            width = 3.dp,
                            color = if (isSelected) Primary else Color.Transparent,
                            shape = CircleShape
                        )
                        .padding(if (isSelected) 6.dp else 0.dp)
                ) {
                    Image(
                        painter = painterResource(id = avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = stringResource(R.string.confirm),
            onClick = { onConfirm(selectedAvatar) },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}