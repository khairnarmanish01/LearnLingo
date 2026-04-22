package com.example.learnlingo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnlingo.presentation.onboarding.screens.AgeSelectionScreen
import com.example.learnlingo.presentation.onboarding.screens.AvatarSelectionScreen
import com.example.learnlingo.presentation.onboarding.screens.LanguagePreferenceScreen
import com.example.learnlingo.presentation.onboarding.screens.LevelSelectionScreen
import com.example.learnlingo.presentation.onboarding.screens.MotivationSelectionScreen
import com.example.learnlingo.presentation.onboarding.screens.TimeSelectionScreen
import com.example.learnlingo.presentation.onboarding.screens.ChooseTopicScreen
import com.example.learnlingo.presentation.onboarding.screens.ChooseTutorScreen
import com.example.learnlingo.presentation.onboarding.screens.SkillsToFocusScreen
import com.example.learnlingo.presentation.onboarding.screens.StartTimeSetupScreen
import com.example.learnlingo.presentation.onboarding.screens.NameInputScreen
import com.example.learnlingo.presentation.onboarding.screens.OnBoardingScreen
import com.example.learnlingo.presentation.onboarding.viewModel.OnboardingViewModel
import com.example.learnlingo.presentation.setup.SetupExperienceScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: OnboardingViewModel
) {

    val startDestination by viewModel.startDestination.collectAsState()

    if (startDestination == null) {
        // Optionally show a splash screen or loading indicator
        return
    }

    NavHost(navController = navController, startDestination = startDestination!!) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen {
                viewModel.updateCurrentStep(Screen.LanguagePreference.route)
                navController.navigate(Screen.LanguagePreference.route)
            }
        }

        composable(route = Screen.LanguagePreference.route) {
            LanguagePreferenceScreen(
                onConfirm = { learn, native ->
                    viewModel.saveLanguage(learn, native)
                    navController.navigate(Screen.LevelSelection.route)
                }
            )
        }

        composable(route = Screen.LevelSelection.route) {
            LevelSelectionScreen(
                onConfirm = { current, target ->
                    viewModel.saveLevels(current, target)
                    navController.navigate(Screen.AgeSelection.route)
                }
            )
        }


        composable(route = Screen.AgeSelection.route) {
            AgeSelectionScreen(
                onBack = { navController.popBackStack() },
                onContinue = { age ->
                    viewModel.saveAge(age)
                    navController.navigate(Screen.AvatarSelection.route)
                }
            )
        }

        composable(route = Screen.AvatarSelection.route) {
            AvatarSelectionScreen(
                onBack = { navController.popBackStack() },
                onConfirm = { avatarRes ->
                    viewModel.saveAvatar(avatarRes)
                    navController.navigate(Screen.NameInput.route)
                }
            )
        }

        composable(route = Screen.NameInput.route) {
            val onboardingData by viewModel.onboardingData.collectAsState()
            NameInputScreen(
                selectedAvatarRes = onboardingData.avatarRes ?: 0,
                onBack = { navController.popBackStack() },
                onConfirm = { name ->
                    viewModel.saveName(name)
                    navController.navigate(Screen.MotivationSelection.route)
                }
            )
        }

        composable(route = Screen.MotivationSelection.route) {
            val onboardingData by viewModel.onboardingData.collectAsState()
            MotivationSelectionScreen(
                selectedLanguage = onboardingData.learnLanguage ?: "English",
                onBack = { navController.popBackStack() },
                onContinue = { motivation ->
                    viewModel.saveMotivation(motivation)
                    navController.navigate(Screen.TimeSelection.route)
                }
            )
        }

        composable(route = Screen.TimeSelection.route) {
            TimeSelectionScreen(
                onBack = { navController.popBackStack() },
                onContinue = { time ->
                    viewModel.saveStudyTime(time)
                    navController.navigate(Screen.StartTimeSetup.route)
                }
            )
        }

        composable(route = Screen.StartTimeSetup.route) {
            StartTimeSetupScreen(
                onBack = { navController.popBackStack() },
                onContinue = { startTime ->
                    viewModel.saveStartTime(startTime)
                    navController.navigate(Screen.SkillsToFocus.route)
                }
            )
        }

        composable(route = Screen.SkillsToFocus.route) {
            SkillsToFocusScreen(
                onBack = { navController.popBackStack() },
                onContinue = { skill ->
                    viewModel.saveFocusSkill(skill)
                    navController.navigate(Screen.ChooseTopic.route)
                }
            )
        }

        composable(route = Screen.ChooseTopic.route) {
            ChooseTopicScreen(
                onBack = { navController.popBackStack() },
                onContinue = { topic ->
                    viewModel.saveChosenTopic(topic)
                    navController.navigate(Screen.ChooseTutor.route)
                }
            )
        }

        composable(route = Screen.ChooseTutor.route) {
            ChooseTutorScreen(
                onBack = { navController.popBackStack() },
                onContinue = { tutorRes ->
                    viewModel.saveTutor(tutorRes)
                    navController.navigate(Screen.SetupExperience.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.SetupExperience.route) {
            SetupExperienceScreen()
        }
    }
}