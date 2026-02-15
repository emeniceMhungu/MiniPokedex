plugins {
    alias(libs.plugins.minipokedex.android.application)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.application.compose)
    alias(libs.plugins.minipokedex.android.application.jacoco)
    alias(libs.plugins.minipokedex.android.application.test)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.minipokedex"

    defaultConfig {
        applicationId = "com.assessment.minipokedex"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
dependencies {
    implementation(project(":feature:pokedex:ui"))
    implementation(project(":feature:pokedex:data"))
}