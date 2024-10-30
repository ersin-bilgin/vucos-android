// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    alias(libs.plugins.navigation.safeargs) apply false
}
