import com.android.build.api.dsl.LibraryExtension
import com.assessment.minipokedex.helper.configureCommonTestingDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests.isIncludeAndroidResources = true
                    animationsDisabled = true
                }
            }

            configureCommonTestingDependencies()
        }
    }
}