plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt.android)
    id("kotlin-parcelize")
    alias(libs.plugins.navigation.safeargs)
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "io.vucos.mobile"
    compileSdk = 35

    defaultConfig {
        applicationId = "io.vucos.mobile"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
        //compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion  = libs.versions.composeCompiler.get()
    }

    kapt {
        correctErrorTypes = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    sourceSets {
        getByName("main") {
            res {
                srcDirs("src\\main\\res", "src\\main\\res\\layouts",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\home", "src\\main\\res", "src\\main\\res\\layouts\\items",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments", "src\\main\\res", "src\\main\\res\\layouts\\fragments\\home",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\util", "src\\main\\res", "src\\main\\res\\layouts\\login",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\login", "src\\main\\res", "src\\main\\res\\layouts\\fragments\\signin",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\profiles", "src\\main\\res", "src\\main\\res\\layouts\\fragments\\who_will_watch",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\who_will_watch", "src\\main\\res", "src\\main\\res\\layouts\\fragments\\home_main",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\search",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\search",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\notifications",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\live_tv",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\my_list",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\movie_details",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\home",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\signup",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\terms_and_privacy_policy",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\movie_details",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\movie_player",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\shimmers",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\play_movie_video",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\fragments\\for_you",
                    "src\\main\\res",
                    "src\\main\\res\\layouts\\items\\genres"
                )
            }
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.firebase.analytics)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.app.compat)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.country.code.picker)
    implementation(libs.retrofitConverterJson)
    implementation(libs.material)
    implementation(libs.dagger.hilt)
    implementation(libs.datastore)
    implementation(libs.palette)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.ui)
    implementation(libs.media3.hls)
    implementation(libs.shimmer)
    kapt(libs.hilt.compiler)
    implementation(libs.grid.layout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.onesignal)
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.splashscreen)
    implementation(libs.lottie)
    implementation(libs.constraint.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
}