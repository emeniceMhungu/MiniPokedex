package com.assessment.minipokedex.helper

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import java.io.FileInputStream
import java.util.Properties


internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    // Read properties from gradle.properties using the lazy and type-safe providers API
    val compileSdk = providers.gradleProperty("android.compileSdk").map(String::toInt)
    val minSdk = providers.gradleProperty("android.minSdk").map(String::toInt)
    val javaVersionProvider = providers.gradleProperty("kotlin.jvmTarget")
    val baseUrl = providers.gradleProperty("base.url").get()
    val apiVersion = providers.gradleProperty("api.version").get()

    commonExtension.apply {
        this.compileSdk = compileSdk.get()
        defaultConfig {
            this.minSdk = minSdk.get()
            buildConfigField(
                "String",
                "ApiVersion",
                "\"$apiVersion\""
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"$baseUrl\""
            )
            /*
            applicationId = configured in module
            versionCode = configured in module
            versionName = configured in module
             */
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        }
        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        // Add packaging options to resolve duplicate file conflicts
        // that are common with testing libraries like JUnit 5.
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
                // Add any other specific files that cause conflicts
                excludes += "META-INF/LICENSE-notice.md"
                excludes += "META-INF/LICENSE.md"
            }
        }
        val compatibilityVersion = javaVersionProvider.map { JavaVersion.toVersion(it) }.get()
        compileOptions {
            sourceCompatibility = compatibilityVersion
            targetCompatibility = compatibilityVersion
        }
    }
    configureKotlinJvm()
}

internal fun Project.configureKotlinJvm() {
    val javaVersionProvider = providers.gradleProperty("kotlin.jvmTarget")
    // The java toolchain is the most robust way to set the JDK for compilation
    // It's part of the 'java' extension, not directly on commonExtension
    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(javaVersionProvider.map { JavaLanguageVersion.of(it) })
        }
    }
    configureKotlin<KotlinAndroidProjectExtension>()
}


private inline fun <reified T : KotlinBaseExtension> Project.configureKotlin() = configure<T> {
    val javaVersionProvider = providers.gradleProperty("kotlin.jvmTarget")
    // Treat all Kotlin warnings as errors (disabled by default)
    // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
    val warningsAsErrors =
        providers.gradleProperty("warningsAsErrors").map { it.toBoolean() }
            .orElse(false)
    when (this) {
        is KotlinAndroidProjectExtension -> compilerOptions
        is KotlinJvmProjectExtension -> compilerOptions
        else -> TODO("Unsupported project extension $this ${T::class}")
    }.apply {
        jvmTarget.set(javaVersionProvider.map { JvmTarget.fromTarget(it) }.get())
        allWarningsAsErrors.set(warningsAsErrors)
        freeCompilerArgs.addAll(
            // Enable experimental coroutines APIs, including Flow
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            /**
             * Remove this args after Phase 3.
             * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-consistent-copy-visibility/#deprecation-timeline
             *
             * Deprecation timeline
             * Phase 3. (Supposedly Kotlin 2.2 or Kotlin 2.3).
             * The default changes.
             * Unless ExposedCopyVisibility is used, the generated 'copy' method has the same visibility as the primary constructor.
             * The binary signature changes. The error on the declaration is no longer reported.
             * '-Xconsistent-data-class-copy-visibility' compiler flag and ConsistentCopyVisibility annotation are now unnecessary.
             */
            "-Xconsistent-data-class-copy-visibility",
            "-Xjsr305=strict",
            "-Xopt-in=kotlin.ExperimentalStdlibApi"
        )

    }
}