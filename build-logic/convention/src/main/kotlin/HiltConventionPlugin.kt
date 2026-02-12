
import com.assessment.minipokedex.helper.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply the Hilt and KSP plugins ONLY after an Android plugin has been applied
            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("com.google.dagger.hilt.android")
                pluginManager.apply("com.google.devtools.ksp")

                dependencies {
                    "implementation"(libs.findLibrary("hilt.android").get())
                    "ksp"(libs.findLibrary("hilt.compiler").get())
                    "implementation"(libs.findLibrary("hilt.navigation.compose").get())
                }
            }

            // Add support for Jvm Module
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    "implementation"(libs.findLibrary("hilt.core").get())
                }
            }
        }
    }
}
