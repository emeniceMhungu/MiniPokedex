import com.assessment.minipokedex.helper.configureCommonTestingDependencies
import com.assessment.minipokedex.helper.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmUnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.jvm")

            configureKotlinJvm()
            configureCommonTestingDependencies()//todo: verify if this is needed
        }
    }
}