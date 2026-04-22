package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.AvatarView
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.ui.theme.LightGray
import com.example.learnlingo.ui.theme.Manrope
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.Secondary
import com.example.learnlingo.ui.theme.TextColor

@Composable
fun OnBoardingScreen(
    onGetStarted: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AvatarView(size = 167.dp, borderSize = 4.dp)

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Title Text
        Text(
            text = stringResource(R.string.go_speak),
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 57.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp,
                brush = Brush.horizontalGradient(colors = listOf(Secondary, Primary))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.let_s_create_your_individual_learning_plan),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                color = TextColor,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(54.dp))

        PrimaryButton(
            text = stringResource(R.string.get_started),
            onClick = onGetStarted,
            modifier = Modifier.padding(horizontal = 32.dp),
            trailingIcon = painterResource(R.drawable.ic_arrow_forward)
        )

        Spacer(modifier = Modifier.height(24.dp))

        val annotatedText = buildAnnotatedString {
            append(stringResource(R.string.already_have_an_account) + " ")

            pushStringAnnotation(
                tag = "SIGN_IN",
                annotation = "sign_in_clicked"
            )
            withStyle(
                style = SpanStyle(color = Primary)
            ) {
                append(stringResource(R.string.sign_in))
            }
            pop()
        }

        ClickableText(
            text = annotatedText,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                fontFamily = Manrope,
                color = LightGray,
                fontWeight = FontWeight.Bold,
            ),
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "SIGN_IN",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    //Todo 👉 Handle Sign In click here
                }
            }
        )
    }
}