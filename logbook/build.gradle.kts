plugins {
    kotlin("kapt")
    alias(libs.plugins.android.library.plugin)
    alias(libs.plugins.hilt.android.plugin)
    alias(libs.plugins.kotlin.android.plugin)
}

android {
    namespace = "com.ogdev.mysugrlogbook.logbook"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    testOptions.unitTests {
        isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":core"))

    implementation(libs.activity.compose)
    implementation(libs.android.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.material3)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))

    implementation(libs.room)
    kapt(libs.roomCompiler)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.androidx.test.manifest)

    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.compose.ui.test)
    testImplementation(libs.coroutines.core)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.robolectric)


}

kapt {
    correctErrorTypes = true
}