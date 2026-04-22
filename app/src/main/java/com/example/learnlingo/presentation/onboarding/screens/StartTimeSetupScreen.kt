package com.example.learnlingo.presentation.onboarding.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.presentation.components.TopBarProgressChat
import com.example.learnlingo.ui.theme.Inter
import com.example.learnlingo.ui.theme.Manrope
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.Secondary

@Composable
fun StartTimeSetupScreen(
    onBack: () -> Unit, onContinue: (String) -> Unit
) {
    val context = LocalContext.current
    var hour by remember { mutableStateOf("") }
    var minute by remember { mutableStateOf("") }
    var showNotificationDialog by remember { mutableStateOf(false) }


    val activity = context as? ComponentActivity

    fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }

    val isContinueEnabled = hour.isNotEmpty() && minute.isNotEmpty() && hour.toIntOrNull()
        ?.let { it in 0..23 } == true && minute.toIntOrNull()?.let { it in 0..59 } == true

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onContinue("$hour:$minute")
            showNotificationDialog = false
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permission = Manifest.permission.POST_NOTIFICATIONS
                val shouldShowRationale =
                    activity?.shouldShowRequestPermissionRationale(permission) ?: true

                if (!shouldShowRationale) {
                    openAppSettings()
                }
            }
            showNotificationDialog = false
        }
    }

    fun handleContinue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(
                    context, permission
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                onContinue("$hour:$minute")
            } else {
                showNotificationDialog = true
            }
        } else {
            onContinue("$hour:$minute")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopBarProgressChat(
                progress = 0.70f,
                chatBubbleText = stringResource(R.string.when_do_you_want_to_start_your_study),
                onBack = onBack
            )

            Spacer(modifier = Modifier.height(130.dp))

            // Time Selection Card
            Surface(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.add_learning_time),
                        fontSize = 20.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1B1F)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TimeInputBox(
                            value = hour,
                            onValueChange = { input ->
                                val digits = input.filter { it.isDigit() }
                                if (digits.isEmpty()) {
                                    hour = digits
                                }
                                else if (digits.length <= 2) {
                                    val numValue = digits.toIntOrNull()
                                    if (numValue != null && numValue <= 23) {
                                        hour = digits
                                    }
                                }
                            },
                            label = stringResource(R.string.hour_label)
                        )

                        Text(
                            text = ":",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF74777F),
                            modifier = Modifier.padding(bottom = 20.dp)
                        )

                        TimeInputBox(
                            value = minute,
                            onValueChange = { input ->
                                val digits = input.filter { it.isDigit() }

                                if (digits.isEmpty()) {
                                    minute = digits
                                } else if (digits.length <= 2) {
                                    val numValue = digits.toIntOrNull()
                                    if (numValue != null && numValue <= 59) {
                                        minute = digits
                                    }
                                }
                            },
                            label = stringResource(R.string.minute_label)
                        )
                    }

                    if (isContinueEnabled) {
                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = stringResource(R.string.we_will_notify_you_before_starting),
                            fontSize = 14.sp,
                            fontFamily = Nunito,
                            color = Color(0xFF64748B),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = stringResource(R.string.continue_text),
                onClick = { handleContinue() },
                enabled = isContinueEnabled,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 32.dp)
            )
        }

        if (showNotificationDialog) {
            NotificationPermissionDialog(onAllow = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    onContinue("$hour:$minute")
                    showNotificationDialog = false
                }
            }, onSkip = {
                onContinue("$hour:$minute")
                showNotificationDialog = false
            }, onDismiss = {
                showNotificationDialog = false
            })
        }
    }
}

@Composable
fun TimeInputBox(
    value: String, onValueChange: (String) -> Unit, label: String
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(width = 72.dp, height = 60.dp)
                .border(
                    width = 2.dp,
                    color = if (isFocused) Primary else Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(6))
                .background(Color.White, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontSize = 23.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    })

            if (value.isEmpty() && !isFocused) {
                Text(
                    text = "00",
                    fontSize = 23.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFABABAF)
                )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            fontSize = 15.sp,
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            color = if (isFocused) Primary else Color(0xFF74777F)
        )
    }
}

@Composable
fun NotificationPermissionDialog(
    onAllow: () -> Unit, onSkip: () -> Unit, onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box {

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Secondary, Primary
                                    )
                                )
                            )
                            .padding(horizontal = 24.dp), horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(24.dp))

                        // Icon
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(
                                    Color.White.copy(0.16f), shape = CircleShape
                                ), contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = stringResource(R.string.allow_notifications),
                            fontSize = 24.sp,
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(R.string.enable_notifications_desc),
                            fontSize = 16.sp,
                            fontFamily = Manrope,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF49454F),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onAllow,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Primary)
                        ) {
                            Text(
                                text = stringResource(R.string.allow),
                                fontSize = 14.sp,
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        TextButton(onClick = onSkip) {
                            Text(
                                text = stringResource(R.string.skip_for_now),
                                fontSize = 14.sp,
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Medium,
                                color = Primary.copy(alpha = 0.75f),
                            )
                        }
                    }

                }

                // Close button
                IconButton(
                    onClick = onDismiss, modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
