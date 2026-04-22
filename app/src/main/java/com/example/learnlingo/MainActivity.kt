package com.example.learnlingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learnlingo.navigation.AppNavGraph
import com.example.learnlingo.presentation.onboarding.viewModel.OnboardingViewModel
import com.example.learnlingo.ui.theme.LearnLingoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val app = (application as LearnLingoApplication)
            val viewModel: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(app.preferenceManager)
            )
            
            LearnLingoTheme {
                AppNavGraph(viewModel = viewModel)
            }
        }
    }
}