plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application.plugin)
    alias(libs.plugins.hilt.android.plugin)
    alias(libs.plugins.kotlin.android.plugin)
}

android {
    namespace = "com.ogdev.mysugrlogbook"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ogdev.mysugrlogbook"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":logbook"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.compose.runtime)

    implementation(libs.room)
    kapt(libs.roomCompiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.espresso.core)
}

kapt {
    correctErrorTypes = true
}