package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.SelectedItem
import com.example.learnlingo.ui.theme.TextColor

data class Topic(val titleRes: Int, val imageRes: Int)

@Composable
fun ChooseTopicScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit
) {
    val context = LocalContext.current
    val topics = listOf(
        Topic(R.string.restaurant, R.drawable.restaurant),
        Topic(R.string.at_the_airport, R.drawable.airport),
        Topic(R.string.business, R.drawable.business),
        Topic(R.string.job_interview, R.drawable.interview),
        Topic(R.string.at_the_doctor, R.drawable.doctor),
        Topic(R.string.relationship, R.drawable.relationship)
    )

    var selectedTopic by remember { mutableStateOf(topics[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 0.85f,
            chatBubbleText = stringResource(R.string.choose_topics_to_practice),
            onBack = onBack
        )

        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(topics) { topic ->
                TopicItem(
                    topic = topic,
                    isSelected = selectedTopic == topic,
                    onClick = { selectedTopic = topic }
                )
            }
        }

        PrimaryButton(
            text = stringResource(R.string.continue_text),
            onClick = { onContinue(context.getString(selectedTopic.titleRes)) },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) SelectedItem else Color(0xFFF8FAFC),
        border = if (isSelected) BorderStroke(1.dp, Primary) else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        // Use a single Box as the container to allow overlapping and corner alignment
        Box(modifier = Modifier.fillMaxSize()) {

            // 1. Text positioned at the Top Right
            Text(
                text = stringResource(topic.titleRes),
                fontSize = 14.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = TextColor,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 16.dp, end = 16.dp)
            )

            // 2. Image positioned at the Bottom Left
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(0.8f) // Restrict width so it doesn't overlap the radio button
                    .fillMaxHeight(0.75f), // Restrict height so it doesn't overlap the top text
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomStart
            )

            // 3. Radio Button positioned at the Bottom Right
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(24.dp) // Slightly increased to match the visual weight in the image
                    .border(
                        width = 2.dp,
                        // Made the unselected color slightly darker/bluer so the ring is visible
                        color = if (isSelected) Primary else Color(0xFFD2DDE9), shape = CircleShape
                    )
                    .padding(6.dp), contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Primary, CircleShape)
                    )
                }
            }
        }
    }
}