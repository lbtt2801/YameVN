plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
}

android {
    namespace = "com.lbtt2801.yamevn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lbtt2801.yamevn"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packagingOptions {
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/DEPENDENCIES")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.animation:animation-core:1.6.6")
    implementation("androidx.compose.ui:ui:1.6.6")
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.material:material:1.6.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation ("com.google.android.gms:play-services-auth:20.1.0")
//    implementation("com.google.android.gms:play-services-auth:21.0.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.compose.material3:material3:1.2.1")

    // lifecycle livedata
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")
    implementation("com.squareup:otto:1.3.8")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.3.0")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // AI
    implementation("com.google.cloud:google-cloud-dialogflow:2.4.0")

    // Room
    implementation("androidx.room:room-ktx:2.6.1")

    // One-Tap Sign in with Google
    implementation("com.github.stevdza-san:OneTapCompose:1.0.12")

    // WorkManager
    implementation ("androidx.work:work-runtime-ktx:2.7.1")
}