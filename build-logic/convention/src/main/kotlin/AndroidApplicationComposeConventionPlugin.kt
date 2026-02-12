import com.android.build.api.dsl.ApplicationExtension
import com.assessment.minipokedex.helper.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import kotlin.apply

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            val extension = extensions.getByType<ApplicationExtension>()
            extension.apply {
                configureAndroidCompose(this)
            }
        }
    }
}