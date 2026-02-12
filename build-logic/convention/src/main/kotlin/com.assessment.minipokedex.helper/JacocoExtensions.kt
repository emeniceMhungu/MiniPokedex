package com.assessment.minipokedex.helper

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import com.android.build.api.variant.SourceDirectories
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.Locale


private val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    // Hilt & Dagger
    "**/*_Hilt*.class",
    "**/Hilt_*.class",
    "**/hilt_aggregated_deps/**",
    "**/*_Factory.class",
    "**/*_MembersInjector.class",
    "**/Dagger*Component.class",
    "**/Dagger*Component\$Builder.class",
    // UI Packages and Conventions
    "**/ui/navigation/**",
    "**/ui/components/**",
    "**/ui/screen/**",
    "**/*Screen.class",
    "**/*Route.class",
    "**/*Activity.class",
    "**/*Fragment.class",
    // DI and Models
    "**/di/**",
    "**/model/**",
    "**/entities/**",
    // Generated Compose code
    "**/*Composable.class",
    "**/*Kt.class",
    "**/ComposableSingletons*.class",
    """**/*${'$'}lambda*.class""",
    """**/*${'$'}composable*.class"""
)

internal fun Project.configureJacoco(
    componentsExtension: AndroidComponentsExtension<*, *, *>
) {
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacocoVersion").get().toString()
    }

    componentsExtension.onVariants { variant ->
        val objFactory = project.objects
        val buildDir = layout.buildDirectory.get()
        val allJars: ListProperty<RegularFile> = objFactory.listProperty(RegularFile::class.java)
        val allDirectories: ListProperty<Directory> = objFactory.listProperty(Directory::class.java)

        // Register the JaCoCo report task for this variant
        val reportTask = tasks.register(
            "create${variant.name.capitalise()}CoverageReport",
            JacocoReport::class.java
        ) {
            dependsOn("test${variant.name.capitalise()}UnitTest")

            group = "Reporting"
            description = "Generate Jacoco coverage reports for ${variant.name}."

            val javaDirs = variant.sources.java?.all?.map { it.map {dir -> dir.asFile} }
            val kotlinDirs = variant.sources.kotlin?.all?.map { it.map {dir -> dir.asFile} }
            sourceDirectories.setFrom(files(javaDirs, kotlinDirs))

            classDirectories.setFrom(
                allJars,
                allDirectories.map { dirs ->
                    dirs.map { dir ->
                        objFactory.fileTree().setDir(dir).exclude(coverageExclusions)
                    }
                }
            )


            executionData.setFrom(
                project.fileTree("$buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                    .matching { include("**/*.exec") },

//                project.fileTree("$buildDir/outputs/code_coverage/${variant.name}AndroidTest")
//                    .matching { include("**/*.ec") },
            )

            reports {
                xml.required.set(true)
                html.required.set(true)
            }

        }


        variant.artifacts.forScope(ScopedArtifacts.Scope.PROJECT)
            .use(reportTask)
            .toGet(
                ScopedArtifact.CLASSES,
                { _ -> allJars },
                { _ -> allDirectories }
            )
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JaCoCo + Robolectric
            // https://github.com/robolectric/robolectric/issues/2230
            isIncludeNoLocationClasses = true

            // Required for JDK 11+ with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}

private fun String.capitalise(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}
