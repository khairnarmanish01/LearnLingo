package com.example.learnlingo

import android.app.Application
import com.example.learnlingo.data.PreferenceManager

class LearnLingoApplication : Application() {
    lateinit var preferenceManager: PreferenceManager
        private set

    override fun onCreate() {
        super.onCreate()
        preferenceManager = PreferenceManager(this)
    }
}
