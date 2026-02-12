import com.android.build.api.dsl.ApplicationExtension
import com.assessment.minipokedex.helper.configureCommonAndroidDependencies
import com.assessment.minipokedex.helper.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            // Use getByType for cleaner, type-safe access to the extension
            val extension = extensions.getByType<ApplicationExtension>()
            extension.apply {
                configureKotlinAndroid(this)

                // Corrected typo: defaultConfig
                defaultConfig {
                    // A more robust way to read properties with a clear error message
                    val targetSdkProperty = providers.gradleProperty("android.targetSdk")
                    if (!targetSdkProperty.isPresent) {
                        throw IllegalStateException(
                            "Required property 'android.targetSdk' is not set in your gradle.properties file."
                        )
                    }

                    // Set the targetSdk from the property
                    targetSdk = targetSdkProperty.map { it.toInt() }.get()
                }
            }
            // adding common dependencies
            configureCommonAndroidDependencies()
        }
    }
}