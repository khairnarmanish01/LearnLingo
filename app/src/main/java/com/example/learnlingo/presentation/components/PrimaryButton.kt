package com.example.learnlingo.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: Painter? = null,
    iconSize: Int = 20
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(32.dp),
                spotColor = Primary.copy(alpha = 0.5f),
                ambientColor = Primary.copy(alpha = 0.5f)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White,
            disabledContainerColor = Primary.copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text, fontSize = 18.sp, fontFamily = Nunito, fontWeight = FontWeight.Bold
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(iconSize.dp)
                )
            }
        }
    }
}
