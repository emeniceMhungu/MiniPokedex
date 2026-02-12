package com.assessment.minipokedex.helper

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configures common dependencies for all Android modules.
 */
internal fun Project.configureCommonAndroidDependencies() {

    dependencies {
        "implementation"(libs.findLibrary("androidx-core-ktx").get())
        "implementation"(libs.findLibrary("androidx-lifecycle-runtime-ktx").get()) //todo: check if this is needed in core modules
    }
}

internal fun Project.configureCommonTestingDependencies(){
    dependencies {
        "api"(libs.findLibrary("testing.junit").get())
        "api"(libs.findLibrary("testing.instantiator").get())
        "api"(libs.findLibrary("kotlin.testing.coroutines").get())
        "api"(libs.findLibrary("testing.mockk").get())
        "api"(libs.findLibrary("testing.mockito").get())
        "api"(libs.findLibrary("testing.mockito.kotlin").get())
        "api"(libs.findLibrary("testing.mock.webserver").get())
        "api"(libs.findLibrary("testing.turbine").get())
        "api"(libs.findLibrary("androidx.test.runner").get())
        "api"(libs.findLibrary("androidx.junit.ktx").get())
        "api"(libs.findLibrary("testng.testng").get())
        "api"(libs.findLibrary("androidx.arch.core.testing").get())
        "api"(libs.findLibrary("timber").get())

    }
}