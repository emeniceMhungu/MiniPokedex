plugins {
    alias(libs.plugins.minipokedex.android.library)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.library.compose)
    alias(libs.plugins.minipokedex.android.library.jacoco)
    alias(libs.plugins.minipokedex.android.library.test)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.pokedex.ui"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":feature:pokedex:domain"))
    implementation(project(":core:common"))
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}