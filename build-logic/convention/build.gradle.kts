import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl` // allows writing Gradle plugins in Kotlin
}

group = "com.assessment.minipokedex.buildlogic"
val javaVersionProvider = providers.gradleProperty("kotlin.jvmTarget")
val compatibilityVersion = javaVersionProvider.map { JavaVersion.toVersion(it) }.get()

// Configure the build-logic plugins to target JDK in build-logic gradle.properties
// This matches the JDK used to build the project, if project level gradle.properties are similar,
// and is not related to what is running on device.
java {
    sourceCompatibility = compatibilityVersion
    targetCompatibility = compatibilityVersion
}
//
kotlin {
    compilerOptions {
        jvmTarget = javaVersionProvider.map { JvmTarget.fromTarget(it) }.get()
    }
}
//
dependencies {
    compileOnly(libs.android.gradle.api.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.hilt.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
//    lintChecks(libs.androidx.lint.gradle)

}
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
//            id = libs.plugins.minipokedex.android.application.get().pluginId
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
//            id = libs.plugins.minipokedex.android.library.get().pluginId
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidApplicationCompose") {
//            id = libs.plugins.minipokedex.android.application.compose.get().pluginId
             id = "convention.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
//            id = libs.plugins.minipokedex.android.library.compose.get().pluginId
            id = "convention.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidApplicationTest") {
//            id = libs.plugins.minipokedex.android.application.test.get().pluginId
            id = "convention.android.application.test"
            implementationClass = "AndroidApplicationTestConventionPlugin"
        }

        register("androidLibraryTest") {
//            id = libs.plugins.minipokedex.android.library.test.get().pluginId
            id = "convention.android.library.test"
            implementationClass = "AndroidLibraryTestConventionPlugin"

        }

        register("kotlinJvmUnitTest"){
//            id = libs.plugins.minipokedex.kotlin.jvm.unit.test.get().pluginId
            id = "convention.kotlin.jvm.unit.test"
            implementationClass = "KotlinJvmUnitTestConventionPlugin"

        }
        register("hilt") {
            //            id = libs.plugins.minipokedex.hilt.get().pluginId
            id = "convention.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("network") {
//            id = libs.plugins.minipokedex.network.get().pluginId
            id = "convention.network"
            implementationClass = "NetworkingConventionPlugin"
        }

        register("room") {
            //            id = libs.plugins.minipokedex.room.get().pluginId
            id = "convention.room"
            implementationClass ="AndroidRoomConventionPlugin"
        }
        register("androidLint") {
//            id = libs.plugins.minipokedex.android.lint.get().pluginId
            id = "convention.android.lint"
            implementationClass = "AndroidLintConventionPlugin"

        }

        register("androidLibraryJacoco"){
            // id = libs.plugins.minipokedex.android.library.jacoco.get().pluginId
            id = "convention.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"

        }
        register("androidApplicationJacoco"){
            // id = libs.plugins.minipokedex.android.application.jacoco.get().pluginId
            id = "convention.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"

        }
    }
}