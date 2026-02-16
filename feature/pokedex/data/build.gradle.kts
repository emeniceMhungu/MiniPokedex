plugins {
    alias(libs.plugins.minipokedex.android.library)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.library.jacoco)
    alias(libs.plugins.minipokedex.android.library.test)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.pokedex.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":feature:pokedex:domain"))
    implementation(project(":core:common"))
}