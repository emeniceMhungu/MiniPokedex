plugins {
    alias(libs.plugins.minipokedex.android.library)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.library.jacoco)
    alias(libs.plugins.minipokedex.android.library.test)
    alias(libs.plugins.minipokedex.network)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.network"
}

dependencies {
    implementation(project(":core:common"))
}