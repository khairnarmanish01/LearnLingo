package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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

data class SkillOption(val titleRes: Int, val iconRes: Int)

@Composable
fun SkillsToFocusScreen(
    onBack: () -> Unit, onContinue: (String) -> Unit
) {
    val context = LocalContext.current
    val options = listOf(
        SkillOption(R.string.pronunciation, R.drawable.ic_pronounciation),
        SkillOption(R.string.speaking_with_confidence, R.drawable.ic_speak_with_confidence),
        SkillOption(R.string.vocabulary, R.drawable.ic_vocabulary),
        SkillOption(R.string.listening, R.drawable.ic_listening),
        SkillOption(R.string.grammar, R.drawable.ic_grammar),
        SkillOption(R.string.writing, R.drawable.ic_writting),
        SkillOption(R.string.reading, R.drawable.ic_reading)
    )

    var selectedOption by remember { mutableStateOf(options[1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 0.75f,
            chatBubbleText = stringResource(R.string.what_skills_do_you_want_to_focus_on),
            onBack = onBack
        )

        Spacer(Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
        ) {
            items(options) { option ->
                SkillItem(
                    option = option,
                    isSelected = selectedOption == option,
                    onClick = { selectedOption = option })
            }
        }

        PrimaryButton(
            text = stringResource(R.string.continue_text),
            onClick = { onContinue(context.getString(selectedOption.titleRes)) },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
        )
    }
}

@Composable
fun SkillItem(
    option: SkillOption, isSelected: Boolean, onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) SelectedItem else Color(0xFFF8FAFC),
        border = if (isSelected) BorderStroke(1.dp, Primary) else null,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (isSelected) 6.dp else 0.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Primary.copy(alpha = 0.4f),
                spotColor = Primary.copy(alpha = 0.6f)
            )
    ) {
        Row(
            modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon in circle
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .shadow(
                        elevation = if (isSelected) 0.dp else 4.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .background(Color.White), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(option.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = stringResource(option.titleRes),
                fontSize = 18.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = TextColor,
            )
        }
    }
}
