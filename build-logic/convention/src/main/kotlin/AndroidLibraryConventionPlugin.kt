import com.android.build.api.dsl.LibraryExtension
import com.assessment.minipokedex.helper.configureCommonAndroidDependencies
import com.assessment.minipokedex.helper.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "kotlin-parcelize")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            // adding common dependencies
            configureCommonAndroidDependencies()
        }
    }
}
