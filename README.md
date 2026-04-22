# LearnLingo

LearnLingo is a premium language learning application built with modern Android technologies. It features an interactive AI conversation interface with real-time lip-sync animations and a comprehensive onboarding flow.

## 🚀 Setup Steps

To get the project running locally, follow these steps:

1.  **Clone the Repository**:
    ```bash
    git clone https://github.com/khairnarmanish01/LearnLingo.git
    ```
2.  **Open in Android Studio**:
    *   Launch Android Studio (Ladybug or newer recommended).
    *   Select **Open** and navigate to the `LearnLingo` project folder.
3.  **Sync Gradle**:
    *   Wait for the project to finish syncing. Ensure you have an active internet connection to download dependencies.
4.  **Run the App**:
    *   Connect a physical device or start an emulator (API 24 or higher).
    *   Click the **Run** button (green play icon) in the toolbar.

## 🏗 Architecture Summary

The project follows the **MVVM (Model-View-ViewModel)** architectural pattern, leveraging **Jetpack Compose** for the UI, **Navigation Compose** for screen transitions, and **DataStore** for persistent state management.

## ✨ Features Implemented

-   **Interactive Onboarding Flow**:
    -   Language selection (Source & Target).
    -   Proficiency level selection.
    -   Daily goal/start time setup with notification permissions.
    -   Skill focus selection (Speaking, Listening, Grammar, etc.).
-   **AI Conversation Interface**:
    -   Dynamic chat bubbles with premium dark-mode styling.
    -   Real-time translation and Text-to-Speech (TTS) for messages.
    -   Custom playback highlighting for message audio.
-   **Avatar Lip-Sync Engine**:
    -   Real-time, offline audio analysis (FFT/RMS) to drive mouth animations.
    -   Viseme mapping for 2D layered PNG avatars.
    -   Smooth transitions between 21 detailed mouth shapes.
-   **Permissions Management**:
    -   Graceful handling of Camera and Microphone permissions with custom UI dialogs.
-   **Persistence**:
    -   `PreferenceManager` using AndroidX DataStore for robust user settings storage.

## ⚖️ Assumptions & Tradeoffs

-   **Offline First Lip-Sync**: Chose an offline audio processing approach for lip-sync to ensure zero latency and user privacy, trading off the extreme accuracy of cloud-based AI models for a more responsive local experience.
-   **DataStore over Room**: Used `DataStore Preferences` for state management instead of a full SQL database, assuming the current data complexity remains low (mostly user settings and progress).
-   **Incremental Localization**: Localization of the AI conversation history is handled lazily; when a user changes languages, previous messages remain in their original language while new ones are generated in the target language.
-   **Design-Centric Development**: Prioritized high-fidelity animations and "premium" aesthetics (glassmorphism, custom gradients) over standard Material Design components to provide a unique user experience.