plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt") // If using annotations like Dagger, Hilt, etc.
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.gms.google-services")// ADD THIS LINE (Compose Compiler plugin)
  //  id 'com.android.application'
   // id ("com.google.gms.google-services")  // ✅ Firebase Plugin

}

android {
    //namespace = "com.example.mekelleuniversitystudentrepresentativeelection"

        namespace = "com.example.musr_votehive" // Add this if missing

// Required in AGP 8.0+
       compileSdk = 35  // Ensure correct SDK version

    defaultConfig {
        //applicationId = ""
        applicationId ="com.example.musr_votehive"

        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0" // Ensure correct version
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    implementation ("androidx.recyclerview:recyclerview:1.3.1")// For RecyclerView

       // implementation 'androidx.appcompat:appcompat:1.6.1' // For AppCompatActivity
       // implementation 'com.google.android.material:material:1.9.0' // For Material Design components

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.material:material:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.androidx.recyclerview)
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.0")

    // Firebase dependencies
    implementation("com.google.firebase:firebase-analytics-ktx:21.0.0")
    implementation("com.google.firebase:firebase-auth-ktx:21.0.1")
    implementation("com.google.firebase:firebase-analytics")

    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))
    // Add other dependencies as required
}

apply(plugin = "com.google.gms.google-services") // Apply Firebase plugin
