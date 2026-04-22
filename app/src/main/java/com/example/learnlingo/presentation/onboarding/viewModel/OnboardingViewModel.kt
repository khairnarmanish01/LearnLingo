package com.example.learnlingo.presentation.onboarding.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.learnlingo.data.OnboardingData
import com.example.learnlingo.data.PreferenceManager
import com.example.learnlingo.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class OnboardingViewModel(private val preferenceManager: PreferenceManager) : ViewModel() {

    private val _onboardingData = MutableStateFlow(
        OnboardingData(
            null, null, null, null, null, null, null, null, null, null, null, null, null
        )
    )
    val onboardingData: StateFlow<OnboardingData> = _onboardingData.asStateFlow()

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            preferenceManager.onboardingData.collect { data ->
                _onboardingData.value = data
            }
        }
        viewModelScope.launch {
            val currentStep = preferenceManager.currentStep.first()
            _startDestination.value = currentStep ?: Screen.OnBoarding.route
        }
    }

    fun saveLanguage(learn: String, native: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(learnLanguage = learn, nativeLanguage = native)
            preferenceManager.saveCurrentStep(Screen.LevelSelection.route)
        }
    }

    fun saveLevels(current: String, target: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(currentLevel = current, targetLevel = target)
            preferenceManager.saveCurrentStep(Screen.AgeSelection.route)
        }
    }

    fun saveMotivation(motivation: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(motivation = motivation)
            preferenceManager.saveCurrentStep(Screen.TimeSelection.route)
        }
    }

    fun saveStudyTime(studyTime: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(studyTime = studyTime)
            preferenceManager.saveCurrentStep(Screen.StartTimeSetup.route)
        }
    }

    fun saveStartTime(startTime: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(startTime = startTime)
            preferenceManager.saveCurrentStep(Screen.SkillsToFocus.route)
        }
    }

    fun saveFocusSkill(skill: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(focusSkill = skill)
            preferenceManager.saveCurrentStep(Screen.ChooseTopic.route)
        }
    }

    fun saveChosenTopic(topic: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(chosenTopic = topic)
            preferenceManager.saveCurrentStep(Screen.ChooseTutor.route)
        }
    }

    fun saveTutor(tutorRes: Int) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(chosenTutor = tutorRes)
            preferenceManager.saveOnboardingCompleted(true)
            preferenceManager.saveCurrentStep(Screen.SetupExperience.route)
        }
    }

    fun saveAge(age: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(ageRange = age)
            preferenceManager.saveCurrentStep(Screen.AvatarSelection.route)
        }
    }

    fun saveAvatar(avatarRes: Int) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(avatarRes = avatarRes)
            preferenceManager.saveCurrentStep(Screen.NameInput.route)
        }
    }

    fun saveName(name: String) {
        viewModelScope.launch {
            preferenceManager.saveOnboardingData(userName = name)
            preferenceManager.saveCurrentStep(Screen.MotivationSelection.route)
        }
    }

    fun updateCurrentStep(step: String) {
        viewModelScope.launch {
            preferenceManager.saveCurrentStep(step)
        }
    }

    class Factory(private val preferenceManager: PreferenceManager) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return OnboardingViewModel(preferenceManager) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}