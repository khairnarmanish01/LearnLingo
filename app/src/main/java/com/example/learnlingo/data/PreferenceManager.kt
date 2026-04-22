package com.example.learnlingo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class PreferenceManager(private val context: Context) {

    companion object {
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val CURRENT_ONBOARDING_STEP = stringPreferencesKey("current_onboarding_step")
        val LEARN_LANGUAGE = stringPreferencesKey("learn_language")
        val NATIVE_LANGUAGE = stringPreferencesKey("native_language")
        val CURRENT_LEVEL = stringPreferencesKey("current_level")
        val TARGET_LEVEL = stringPreferencesKey("target_level")
        val AGE_RANGE = stringPreferencesKey("age_range")
        val SELECTED_AVATAR = intPreferencesKey("selected_avatar")
        val USER_NAME = stringPreferencesKey("user_name")
        val MOTIVATION = stringPreferencesKey("motivation")
        val STUDY_TIME = stringPreferencesKey("study_time")
        val START_TIME = stringPreferencesKey("start_time")
        val FOCUS_SKILL = stringPreferencesKey("focus_skill")
        val CHOSEN_TOPIC = stringPreferencesKey("chosen_topic")
        val CHOSEN_TUTOR = intPreferencesKey("chosen_tutor")
    }

    suspend fun saveOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = completed
        }
    }

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[ONBOARDING_COMPLETED] ?: false
    }

    suspend fun saveCurrentStep(step: String) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_ONBOARDING_STEP] = step
        }
    }

    val currentStep: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[CURRENT_ONBOARDING_STEP]
    }

    suspend fun saveOnboardingData(
        learnLanguage: String? = null,
        nativeLanguage: String? = null,
        currentLevel: String? = null,
        targetLevel: String? = null,
        ageRange: String? = null,
        avatarRes: Int? = null,
        userName: String? = null,
        motivation: String? = null,
        studyTime: String? = null,
        startTime: String? = null,
        focusSkill: String? = null,
        chosenTopic: String? = null,
        chosenTutor: Int? = null
    ) {
        context.dataStore.edit { preferences ->
            learnLanguage?.let { preferences[LEARN_LANGUAGE] = it }
            nativeLanguage?.let { preferences[NATIVE_LANGUAGE] = it }
            currentLevel?.let { preferences[CURRENT_LEVEL] = it }
            targetLevel?.let { preferences[TARGET_LEVEL] = it }
            ageRange?.let { preferences[AGE_RANGE] = it }
            avatarRes?.let { preferences[SELECTED_AVATAR] = it }
            userName?.let { preferences[USER_NAME] = it }
            motivation?.let { preferences[MOTIVATION] = it }
            studyTime?.let { preferences[STUDY_TIME] = it }
            startTime?.let { preferences[START_TIME] = it }
            focusSkill?.let { preferences[FOCUS_SKILL] = it }
            chosenTopic?.let { preferences[CHOSEN_TOPIC] = it }
            chosenTutor?.let { preferences[CHOSEN_TUTOR] = it }
        }
    }

    val onboardingData: Flow<OnboardingData> = context.dataStore.data.map { preferences ->
        OnboardingData(
            learnLanguage = preferences[LEARN_LANGUAGE],
            nativeLanguage = preferences[NATIVE_LANGUAGE],
            currentLevel = preferences[CURRENT_LEVEL],
            targetLevel = preferences[TARGET_LEVEL],
            ageRange = preferences[AGE_RANGE],
            avatarRes = preferences[SELECTED_AVATAR],
            userName = preferences[USER_NAME],
            motivation = preferences[MOTIVATION],
            studyTime = preferences[STUDY_TIME],
            startTime = preferences[START_TIME],
            focusSkill = preferences[FOCUS_SKILL],
            chosenTopic = preferences[CHOSEN_TOPIC],
            chosenTutor = preferences[CHOSEN_TUTOR]
        )
    }
}

data class OnboardingData(
    val learnLanguage: String?,
    val nativeLanguage: String?,
    val currentLevel: String?,
    val targetLevel: String?,
    val ageRange: String?,
    val avatarRes: Int?,
    val userName: String?,
    val motivation: String?,
    val studyTime: String?,
    val startTime: String?,
    val focusSkill: String?,
    val chosenTopic: String?,
    val chosenTutor: Int?
)
