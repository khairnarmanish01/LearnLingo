package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.AvatarView
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.ui.theme.ItemTextColor
import com.example.learnlingo.ui.theme.LanguageItemBg
import com.example.learnlingo.ui.theme.Manrope
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.SelectedItem
import com.example.learnlingo.ui.theme.TextDark

data class LanguageOption(val name: String, val flagRes: Int)

@Composable
fun LanguagePreferenceScreen(onConfirm: (learn: String, native: String) -> Unit) {
    val languages = listOf(
        LanguageOption(stringResource(R.string.spanish), R.drawable.flag_spanish),
        LanguageOption(stringResource(R.string.english), R.drawable.flag_english),
        LanguageOption(stringResource(R.string.french), R.drawable.flag_french)
    )

    var selectedLearnLanguage by remember { mutableStateOf(languages[0]) }
    var selectedNativeLanguage by remember { mutableStateOf(languages[1]) }

    var isLearnDropdownExpanded by remember { mutableStateOf(false) }
    var isNativeDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 80.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AvatarView(size = 125.dp, borderSize = 3.dp)

        Spacer(modifier = Modifier.height(47.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            LanguageSelectorField(
                label = stringResource(R.string.you_want_to_learn),
                selectedOption = selectedLearnLanguage,
                options = languages.filter { it != selectedNativeLanguage },
                isExpanded = isLearnDropdownExpanded,
                onExpandedChange = {
                    isLearnDropdownExpanded = it
                    if (it) isNativeDropdownExpanded = false
                },
                onOptionSelected = { selectedLearnLanguage = it })

            Spacer(modifier = Modifier.height(45.dp))

            LanguageSelectorField(
                label = stringResource(R.string.native_language),
                selectedOption = selectedNativeLanguage,
                options = languages.filter { it != selectedLearnLanguage },
                isExpanded = isNativeDropdownExpanded,
                onExpandedChange = {
                    isNativeDropdownExpanded = it
                    if (it) isLearnDropdownExpanded = false
                },
                onOptionSelected = { selectedNativeLanguage = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Confirm Button
        PrimaryButton(
            text = stringResource(R.string.confirm),
            onClick = { onConfirm(selectedLearnLanguage.name, selectedNativeLanguage.name) },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun LanguageSelectorField(
    label: String,
    selectedOption: LanguageOption,
    options: List<LanguageOption>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (LanguageOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = label, style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                color = TextDark
            ), modifier = Modifier.padding(bottom = 8.dp)
        )
        CustomDropdown(
            selectedOption = selectedOption,
            options = options,
            isExpanded = isExpanded,
            onExpandedChange = onExpandedChange,
            onOptionSelected = onOptionSelected
        )
    }
}

@Composable
fun CustomDropdown(
    selectedOption: LanguageOption,
    options: List<LanguageOption>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (LanguageOption) -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f, label = "arrow_rotation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        // Dropdown Header (Main Item) using Surface for tonal elevation
        Surface(
            tonalElevation = 1.dp,
            shape = RoundedCornerShape(16.dp),
            color = LanguageItemBg,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onExpandedChange(!isExpanded) }
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                WhiteBorderFlagImage(selectedOption.flagRes, 4.dp)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = selectedOption.name,
                    fontSize = 16.sp,
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    color = ItemTextColor,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_dropdown),
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(rotationAngle)
                )
            }
        }

        // Dropdown List (Separate from main item)
        AnimatedVisibility(visible = isExpanded) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    tonalElevation = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = LanguageItemBg,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        options.forEach { option ->
                            val isSelected = option == selectedOption
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (isSelected) SelectedItem else Color.Transparent,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                                    .clickable {
                                        onOptionSelected(option)
                                        onExpandedChange(false)
                                    }) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    WhiteBorderFlagImage(option.flagRes, 3.dp)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = option.name,
                                        fontSize = 16.sp,
                                        fontFamily = Nunito,
                                        fontWeight = FontWeight.Bold,
                                        color = ItemTextColor,
                                        modifier = Modifier.weight(1f)
                                    )
                                    if (isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .size(22.dp)
                                                .background(Primary, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_tick),
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(14.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WhiteBorderFlagImage(flagRes: Int, borderSize: Dp) {
    Box(
        Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(borderSize)
    ) {
        Image(
            painter = painterResource(flagRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}