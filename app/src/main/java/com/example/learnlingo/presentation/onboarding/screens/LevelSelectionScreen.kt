package com.example.learnlingo.presentation.onboarding.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlingo.R
import com.example.learnlingo.presentation.components.AvatarView
import com.example.learnlingo.presentation.components.PrimaryButton
import com.example.learnlingo.ui.theme.Blue
import com.example.learnlingo.ui.theme.FaintYellow
import com.example.learnlingo.ui.theme.Green
import com.example.learnlingo.ui.theme.ItemBorderColor
import com.example.learnlingo.ui.theme.ItemTextColor
import com.example.learnlingo.ui.theme.ItemTextSecondary
import com.example.learnlingo.ui.theme.ItemTextSecondaryVariant
import com.example.learnlingo.ui.theme.ItemTextVariant
import com.example.learnlingo.ui.theme.LanguageItemBg
import com.example.learnlingo.ui.theme.LightBlue
import com.example.learnlingo.ui.theme.LightGreen
import com.example.learnlingo.ui.theme.LightYellow
import com.example.learnlingo.ui.theme.Manrope
import com.example.learnlingo.ui.theme.Nunito
import com.example.learnlingo.ui.theme.Primary
import com.example.learnlingo.ui.theme.Purple
import com.example.learnlingo.ui.theme.SelectedItem
import com.example.learnlingo.ui.theme.TextDark
import com.example.learnlingo.ui.theme.White
import com.example.learnlingo.ui.theme.Yellow

data class LevelOption(
    val name: String,
    val description: String,
    val score: String,
    val icon: Int,
    val color: Color
)

enum class Scores {
    A0, A1, A2, B1, B2, C1
}

@Composable
fun LevelSelectionScreen(onConfirm: (current: String, target: String) -> Unit) {

    val currentLevelOptions = listOf(
        LevelOption(
            name = stringResource(R.string.beginner),
            description = stringResource(R.string.beginner_option_desc),
            score = Scores.A0.name,
            icon = R.drawable.ic_beginner,
            color = LightYellow
        ),
        LevelOption(
            name = stringResource(R.string.elementary),
            description = stringResource(R.string.elementary_desc),
            score = Scores.A1.name,
            icon = R.drawable.ic_elementary_current_lev,
            color = LightGreen
        ),
        LevelOption(
            name = stringResource(R.string.pre_intermediate),
            description = stringResource(R.string.pre_intermediate_desc),
            score = Scores.A2.name,
            icon = R.drawable.ic_pre_intermediate_current_lev,
            color = LightBlue
        ),
        LevelOption(
            name = stringResource(R.string.intermediate),
            description = stringResource(R.string.intermediate_desc),
            score = Scores.B1.name,
            icon = R.drawable.ic_intermediate_current_lev,
            color = White
        ),
        LevelOption(
            name = stringResource(R.string.upper_intermediate),
            description = stringResource(R.string.upper_intermediate_desc),
            score = Scores.B2.name,
            icon = R.drawable.ic_upper_intermediate_current_lev,
            color = FaintYellow
        )
    )

    val levelToReachOptions = listOf(
        LevelOption(
            name = stringResource(R.string.elementary),
            description = stringResource(R.string.elementary_reach_desc),
            score = Scores.A2.name,
            icon = R.drawable.ic_elementary_reach,
            color = Green
        ),
        LevelOption(
            name = stringResource(R.string.intermediate),
            description = stringResource(R.string.intermediate_to_reach_desc),
            score = Scores.B1.name,
            icon = R.drawable.ic_intermediate_reach,
            color = Blue
        ),
        LevelOption(
            name = stringResource(R.string.upper_intermediate),
            description = stringResource(R.string.upper_intermediate_reach_desc),
            score = Scores.B2.name,
            icon = R.drawable.ic_upper_intermediate_reach,
            color = Purple
        ),
        LevelOption(
            name = stringResource(R.string.advanced),
            description = stringResource(R.string.advanced_reach_desc),
            score = Scores.C1.name,
            icon = R.drawable.ic_advanced_reach,
            color = Yellow
        )
    )

    var selectedCurrentLevel by remember { mutableStateOf(currentLevelOptions[0]) }
    var selectedLevelToReach by remember { mutableStateOf(levelToReachOptions[1]) }

    var isCurrentDropdownExpanded by remember { mutableStateOf(false) }
    var isReachDropdownExpanded by remember { mutableStateOf(false) }

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
            LevelSelectorField(
                label = stringResource(R.string.whats_your_current_level),
                selectedOption = selectedCurrentLevel,
                options = currentLevelOptions,
                isExpanded = isCurrentDropdownExpanded,
                onExpandedChange = {
                    isCurrentDropdownExpanded = it
                    if (it) isReachDropdownExpanded = false
                },
                onOptionSelected = { selectedCurrentLevel = it }
            )

            Spacer(modifier = Modifier.height(45.dp))

            LevelSelectorField(
                label = stringResource(R.string.what_level_do_you_want_to_reach),
                selectedOption = selectedLevelToReach,
                options = levelToReachOptions,
                isExpanded = isReachDropdownExpanded,
                onExpandedChange = {
                    isReachDropdownExpanded = it
                    if (it) isCurrentDropdownExpanded = false
                },
                onOptionSelected = { selectedLevelToReach = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = stringResource(R.string.confirm),
            onClick = { onConfirm(selectedCurrentLevel.name, selectedLevelToReach.name) },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun LevelSelectorField(
    label: String,
    selectedOption: LevelOption,
    options: List<LevelOption>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (LevelOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                color = TextDark
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LevelCustomDropdown(
            selectedOption = selectedOption,
            options = options,
            isExpanded = isExpanded,
            onExpandedChange = onExpandedChange,
            onOptionSelected = onOptionSelected
        )
    }
}

@Composable
fun LevelCustomDropdown(
    selectedOption: LevelOption,
    options: List<LevelOption>,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onOptionSelected: (LevelOption) -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "arrow_rotation"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
            tonalElevation = 1.dp,
            shape = RoundedCornerShape(16.dp),
            color = LanguageItemBg,
            border = BorderStroke(1.dp, ItemBorderColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onExpandedChange(!isExpanded) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LevelIcon(selectedOption.icon, selectedOption.color)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = selectedOption.name,
                            fontSize = 16.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color = ItemTextColor
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = selectedOption.score,
                            fontSize = 12.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD1D5DB)
                        )
                    }
                    Text(
                        text = selectedOption.description,
                        fontSize = 14.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Normal,
                        color = ItemTextVariant,
                        maxLines = 1
                    )
                }
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
                            val isSelected = option.name == selectedOption.name && option.score == selectedOption.score
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = if (isSelected) SelectedItem else Color.Transparent,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp)
                                    .clickable {
                                        onOptionSelected(option)
                                        onExpandedChange(false)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    LevelIcon(option.icon, option.color)
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = option.name,
                                                fontSize = 16.sp,
                                                fontFamily = Nunito,
                                                fontWeight = FontWeight.Bold,
                                                color = ItemTextColor
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = option.score,
                                                fontSize = 14.sp,
                                                fontFamily = Nunito,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSelected) ItemTextSecondary else ItemTextSecondaryVariant
                                            )
                                        }
                                        Text(
                                            text = option.description,
                                            fontSize = 12.sp,
                                            fontFamily = Nunito,
                                            fontWeight = FontWeight.Normal,
                                            color = ItemTextVariant
                                        )
                                    }
                                    if (isSelected) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .background(Primary, CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.ic_tick),
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(16.dp)
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
fun LevelIcon(iconRes: Int, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
