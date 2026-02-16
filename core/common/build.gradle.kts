plugins {
    alias(libs.plugins.minipokedex.android.library)
    alias(libs.plugins.minipokedex.hilt)
    alias(libs.plugins.minipokedex.android.library.compose)
    alias(libs.plugins.minipokedex.android.library.jacoco)
    alias(libs.plugins.minipokedex.android.library.test)
    alias(libs.plugins.minipokedex.android.lint)
}

android {
    namespace = "com.assessment.common"
}
dependencies {
    implementation(libs.accompanist.permissions)
}