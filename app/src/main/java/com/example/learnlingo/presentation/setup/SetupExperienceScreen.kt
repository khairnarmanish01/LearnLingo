package com.example.learnlingo.presentation.setup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.AvatarView
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.ui.theme.Manrope
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.TextDark
import kotlinx.coroutines.delay

@Composable
fun SetupExperienceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isProcessed by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(2000)
            isProcessed = true
        }

        Spacer(modifier = Modifier.weight(1.2f))

        Box(contentAlignment = Alignment.Center) {
            if (!isProcessed) {
                CircularProgressIndicator(
                    progress = { 0.15f },
                    modifier = Modifier.size(160.dp),
                    color = Color(0xFF4761F0),
                    strokeWidth = 4.dp,
                    trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
                    strokeCap = StrokeCap.Round,
                )
            }

            AvatarView(size = 146.dp, borderSize = if (isProcessed) 4.dp else 0.dp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.title_creating_experience),
            fontSize = 24.sp,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.subtitle_moment),
            fontSize = 14.sp,
            fontFamily = Manrope,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(22.dp))

        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ChecklistItem(text = stringResource(id = R.string.task_diverse_topics))
            ChecklistItem(text = stringResource(id = R.string.task_interactive_dialogues))
            if (isProcessed) {
                ChecklistItem(text = stringResource(id = R.string.optimizing_your_learning_path))
                ChecklistItem(text = stringResource(id = R.string.finalizing_your_plan))
            }
        }

        if (!isProcessed) {
            Spacer(modifier = Modifier.weight(2f))
        } else {
            Spacer(modifier = Modifier.weight(1f)) // Replaced 100.dp height
            InfinitelyScrollingStatsRow()
            Spacer(modifier = Modifier.weight(1f)) // Added to keep the button docked at the bottom
        }

        // Your existing PrimaryButton
        PrimaryButton(
            text = stringResource(id = R.string.btn_continue),
            onClick = { /* Handle Navigation or State update */ },
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}


@Composable
fun ChecklistItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(R.drawable.ic_ticked),
            contentDescription = "Completed",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = Manrope,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun InfinitelyScrollingStatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .basicMarquee(
                iterations = Int.MAX_VALUE, velocity = 75.dp
            )
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        UserCountStack()
        GoalAchievementStat()
        RatingSection()
    }
}


@Composable
fun UserCountStack(
    modifier: Modifier = Modifier, overlapOffset: Int = (-20)
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(overlapOffset.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Replace R.drawable references with your actual image assets
            AvatarItem(imageRes = R.drawable.avt1)
            AvatarItem(imageRes = R.drawable.avt2)
            AvatarItem(imageRes = R.drawable.avt3)
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = stringResource(R.string._5m_users),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Primary
        )
    }
}

@Composable
fun GoalAchievementStat() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.bullseye_img),
            contentDescription = "Target with dart",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = stringResource(R.string.users_reach),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF322F35),
            textAlign = TextAlign.Center,
        )

        Text(
            text = stringResource(R.string.their_goals_in),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF322F35),
            textAlign = TextAlign.Center,
        )


        Text(
            text = stringResource(R.string._16_weeks),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Primary,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun RatingSection(rating: String = "4.8") {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.grain_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.height(80.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = rating,
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )

                Spacer(Modifier.width(8.dp))

                repeat(5) {
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 2.dp)
                    )
                }
            }

            Text(
                text = stringResource(R.string._200k_app_ratings),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF6A7282)
            )

        }

        Image(
            painter = painterResource(R.drawable.grain_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(80.dp)
                .graphicsLayer {
                    scaleX = -1f
                })
    }
}

@Composable
fun AvatarItem(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(3.dp, Color.White, CircleShape)
    )
}