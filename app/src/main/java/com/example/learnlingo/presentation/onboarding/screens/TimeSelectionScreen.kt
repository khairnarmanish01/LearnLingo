package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

data class TimeOption(val titleRes: Int)

@Composable
fun TimeSelectionScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit
) {
    val context = LocalContext.current
    val options = listOf(
        TimeOption(R.string.time_5_mins),
        TimeOption(R.string.time_10_mins),
        TimeOption(R.string.time_15_mins),
        TimeOption(R.string.time_30_mins)
    )

    var selectedOption by remember { mutableStateOf(options[2]) } // 15 mins default

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBarProgressChat(
            progress = 0.65f,
            chatBubbleText = stringResource(R.string.how_many_minutes_do_you_have_time_for_study),
            onBack = onBack
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp)
        ) {
            items(options) { option ->
                TimeItem(
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
fun TimeItem(
    option: TimeOption,
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
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(option.titleRes),
                fontSize = 18.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = TextColor,
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
