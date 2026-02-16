plugins {
    `java-library`
    kotlin("jvm")
    alias(libs.plugins.minipokedex.android.lint)
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.junit)
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.lint.api)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.lint.checks)
    testImplementation(libs.lint.tests)
}