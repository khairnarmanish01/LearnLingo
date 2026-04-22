package com.example.learnlingo.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onboarding")
    object LanguagePreference : Screen("language_selection")
    object LevelSelection : Screen("level_selection")
    object MotivationSelection : Screen("motivation_selection")
    object TimeSelection : Screen("time_selection")
    object StartTimeSetup : Screen("start_time_setup")
    object SkillsToFocus : Screen("skills_to_focus")
    object ChooseTopic : Screen("choose_topic")
    object ChooseTutor : Screen("choose_tutor")
    object AgeSelection : Screen("age_selection")
    object AvatarSelection : Screen("avatar_selection")
    object NameInput : Screen("name_input")
    object SetupExperience : Screen("setup_experience")
}