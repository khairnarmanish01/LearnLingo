package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary

@Composable
fun NameInputScreen(
    selectedAvatarRes: Int, onBack: () -> Unit, onConfirm: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarProgressChat(
            progress = 0.70f,
            chatBubbleText = stringResource(R.string.what_is_your_first_name),
            onBack = onBack
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Selected Avatar
        Box(
            modifier = Modifier
                .size(130.dp)
                .clip(CircleShape)
                .border(3.dp, Primary, CircleShape)
                .padding(6.dp)
        ) {
            Image(
                painter = painterResource(id = if (selectedAvatarRes != 0) selectedAvatarRes else R.drawable.avatar_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Label
        Surface(
            color = Color(0xFFF5EFF7),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(R.string.enter_your_first_name_in_english),
                modifier = Modifier.padding(vertical = 14.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Medium,
                color = Primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input Field
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(
                    text = stringResource(R.string.name_placeholder),
                    color = Color(0xFFD1D5DC),
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8FAFC),
                unfocusedContainerColor = Color(0xFFF8FAFC),
                disabledContainerColor = Color(0xFFF8FAFC),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Primary
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(R.string.confirm),
            onClick = { if (name.isNotBlank()) onConfirm(name) },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp),
            enabled = name.isNotBlank()
        )
    }
}