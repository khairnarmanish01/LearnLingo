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
import androidx.compose.material3.Icon
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

data class MotivationOption(val titleRes: Int, val iconRes: Int)

@Composable
fun MotivationSelectionScreen(
    selectedLanguage: String,
    onBack: () -> Unit,
    onContinue: (String) -> Unit
) {
    val context = LocalContext.current
    val options = listOf(
        MotivationOption(R.string.studies, R.drawable.ic_studies),
        MotivationOption(R.string.work, R.drawable.ic_work),
        MotivationOption(R.string.travel, R.drawable.ic_travel),
        MotivationOption(R.string.living_abroad, R.drawable.ic_living_abroad),
        MotivationOption(R.string.self_improvement, R.drawable.ic_self_improvement),
        MotivationOption(R.string.friends, R.drawable.ic_friends),
        MotivationOption(R.string.relationships, R.drawable.ic_relationships)
    )

    var selectedOption by remember { mutableStateOf(options[1]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 0.65f,
            chatBubbleText = stringResource(R.string.what_motivates_you_to_learn_format, selectedLanguage),
            onBack = onBack
        )

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
        ) {
            items(options) { option ->
                MotivationItem(
                    option = option,
                    isSelected = selectedOption == option,
                    onClick = { selectedOption = option }
                )
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
fun MotivationItem(
    option: MotivationOption,
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
            .shadow(
                elevation = if (isSelected) 6.dp else 0.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Primary.copy(alpha = 0.4f),
                spotColor = Primary.copy(alpha = 0.6f)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(
                        elevation = if (isSelected) 0.dp else 4.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(option.iconRes),
                    contentDescription = null,
                    tint = if (isSelected) Primary else Color(0xFF364153),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = stringResource(option.titleRes),
                fontSize = 18.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.Black else TextColor,
                modifier = Modifier.weight(1f)
            )


            Image(
                painter = painterResource(if (isSelected) R.drawable.ic_radio_selected_bordered else R.drawable.ic_radio_unselected),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
