plugins {
    alias(libs.plugins.minipokedex.android.library)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.library.jacoco)
    alias(libs.plugins.minipokedex.android.library.test)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.pokedex.domain"
}

dependencies {
    implementation(project(":core:common"))
}