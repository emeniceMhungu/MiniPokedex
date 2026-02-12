package com.assessment.minipokedex.helper

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            "implementation"( platform(bom))
            "androidTestImplementation"(platform(bom))

            "implementation"(libs.findLibrary("androidx-compose-ui").get())
            "implementation"(libs.findLibrary("androidx-compose-ui-graphics").get())
            "implementation"(libs.findLibrary( "androidx-compose-ui-tooling-preview").get())
            "implementation"(libs.findLibrary("androidx-compose-material3").get())
            "implementation"(libs.findLibrary("androidx-activity-compose").get())
            "implementation"(libs.findLibrary("androidx.constraintlayout").get())
            "implementation"(libs.findLibrary("androidx.compose.material.icons").get())
            "implementation"(libs.findLibrary("androidx-compose-material3-adaptive-navigation-suite").get())


            "implementation"(libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
        }
    }

    /**
     * It provides the following benefits:
     *  - Performance Optimization: It gives the tools to find and fix sources of unnecessary recomposition.
     *  - Cleanliness: It keeps the build logic centralized and out of individual module build.gradle.kts files.
     *  - Scalability: It works seamlessly across a multi-module project, ensuring consistent build behavior everywhere.
     *
     *  NB: It's important to note  it's a "set it and forget it" configuration that will be incredibly useful the moment
     *  we need to diagnose a performance problem in the UI. It's not required to have this configuration.
     *  For a small, simple project, we might not notice the difference.
     */
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }
        fun Provider<*>.relativeToRootProject(dir: String) = map {
            isolated.rootProject.projectDirectory
                .dir("build")
                .dir(projectDir.toRelativeString(rootDir))
        }.map { it.dir(dir) }

        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)

        stabilityConfigurationFiles
            .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))
    }
}