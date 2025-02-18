buildscript {
    repositories {
        google()       // Required for Firebase and other Google services
        mavenCentral() // For other dependencies
    }

    dependencies {
        // Google Services plugin for Firebase (Keep only one!)
        classpath("com.google.gms:google-services:4.4.2")
       // classpath 'com.google.gms:google-services:4.3.10' // Make sure the version is up-to-date

        // Optional: Firebase Crashlytics plugin
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        // Optional: Kotlin Compose Compiler plugin (only needed in Kotlin 2.0+)
        classpath("androidx.compose.compiler:compiler:1.5.0")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false // Ensure Compose plugin is added
    alias(libs.plugins.google.gms.google.services) apply false
}

allprojects {
    repositories {
       // google()
       // mavenCentral()
    }
}

