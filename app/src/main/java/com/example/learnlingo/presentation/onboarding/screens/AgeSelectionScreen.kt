package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.AgeOptionItemBG
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.SelectedItem
import com.example.learnlingo.ui.theme.TextColor

data class AgeOption(val emoji: String, val range: String)

@Composable
fun AgeSelectionScreen(onBack: () -> Unit, onContinue: (age: String) -> Unit) {
    val ageOptions = listOf(
        AgeOption("\uD83C\uDF12", stringResource(R.string.age_range_0_17)),
        AgeOption("\uD83C\uDF13", stringResource(R.string.age_range_18_24)),
        AgeOption("\uD83C\uDF14", stringResource(R.string.age_range_25_34)),
        AgeOption("\uD83C\uDF15", stringResource(R.string.age_range_35_46)),
        AgeOption("\uD83C\uDF16", stringResource(R.string.age_range_47_59)),
        AgeOption("\uD83C\uDF17", stringResource(R.string.age_range_60_plus)),
    )

    var selectedAge by remember { mutableStateOf(ageOptions[1]) }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {

        TopBarProgressChat(
            progress = 0.42f,
            chatBubbleText = stringResource(R.string.how_old_are_you),
            onBack = onBack
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Selection List
            LazyColumn(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(ageOptions) { ageOption ->
                    AgeOptionItem(
                        emoji = ageOption.emoji,
                        age = ageOption.range,
                        isSelected = selectedAge == ageOption,
                        onClick = { selectedAge = ageOption })
                }
            }

            // 4. Continue Button
            PrimaryButton(
                text = stringResource(R.string.continue_text),
                onClick = { onContinue(selectedAge.range) },
                modifier = Modifier.padding(bottom = 32.dp, top = 16.dp)
            )
        }
    }
}

@Composable
fun AgeOptionItem(emoji: String, age: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        color = if (isSelected) SelectedItem else AgeOptionItemBG,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = emoji,
                fontFamily = Nunito,
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = age,
                modifier = Modifier.weight(1f),
                fontFamily = Nunito,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )

            Image(
                painter = painterResource(if (isSelected) R.drawable.ic_radio_selected else R.drawable.ic_radio_unselected),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}