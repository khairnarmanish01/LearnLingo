package com.example.learnlingo.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.ui.theme.ChatBubbleColor
import com.example.learnlingo.ui.theme.ItemBorderColor
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.ProgressBarPath
import com.example.learnlingo.ui.theme.ProgressGradientEnd
import com.example.learnlingo.ui.theme.ProgressGradientStart
import com.example.learnlingo.ui.theme.TextColor

@Composable
fun TopBarProgressChat(
    progress: Float,
    chatBubbleText: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(50))
                    .background(ProgressBarPath)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(50))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(ProgressGradientStart, ProgressGradientEnd)
                            )
                        )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // 2. Avatar & Speech Bubble
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.ai_avatar_without_bg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(73.dp)
                        .align(Alignment.CenterStart)

                )

                // Bubble
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(start = 67.dp, top = 6.dp)
                        .fillMaxWidth()
                        .clip(ChatBubbleShape())
                        .background(ChatBubbleColor)
                        .border(1.dp, ItemBorderColor, shape = ChatBubbleShape())
                        .padding(12.dp)
                        .padding(start = 22.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = chatBubbleText,
                        fontSize = 16.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Bold,
                        color = TextColor
                    )
                }
            }
        }
    }
}