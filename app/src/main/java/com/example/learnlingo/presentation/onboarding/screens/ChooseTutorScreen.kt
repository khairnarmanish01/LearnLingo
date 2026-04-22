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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.SelectedItem

@Composable
fun ChooseTutorScreen(
    onBack: () -> Unit, onContinue: (Int) -> Unit
) {
    LocalContext.current
    val tutors = listOf(
        R.drawable.ai_tutor_robot_female,
        R.drawable.ai_tutor_robot_male,
        R.drawable.ai_tutor_woman,
        R.drawable.ai_tutor_man
    )

    var selectedTutor by remember { mutableIntStateOf(tutors[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 1f,
            chatBubbleText = stringResource(R.string.choose_your_ai_tutor),
            onBack = onBack
        )

        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tutors) { tutor ->
                TutorItem(
                    tutorRes = tutor,
                    isSelected = selectedTutor == tutor,
                    onClick = { selectedTutor = tutor })
            }
        }

        PrimaryButton(
            text = stringResource(R.string.continue_text),
            onClick = { onContinue(selectedTutor) },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}

@Composable
fun TutorItem(
    tutorRes: Int, isSelected: Boolean, onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFFEF7FF),
        modifier = Modifier
            .fillMaxWidth()
            .height(131.dp)
    ) {
        // Use a single Box as the container to allow overlapping and corner alignment
        Box(modifier = Modifier.fillMaxSize()) {


            // 2. Image positioned at the Bottom Left
            Image(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 8.dp)
                    .fillMaxSize(),
                painter = painterResource(tutorRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(14.dp)
                    .size(22.dp) // Slightly increased to match the visual weight in the image
                    .border(
                        width = 2.dp,
                        // Made the unselected color slightly darker/bluer so the ring is visible
                        color = if (isSelected) Primary else Color(0xFFD2DDE9), shape = CircleShape
                    )
                    .padding(5.dp), contentAlignment = Alignment.Center
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