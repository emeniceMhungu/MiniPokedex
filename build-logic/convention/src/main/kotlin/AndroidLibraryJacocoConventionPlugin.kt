import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.assessment.minipokedex.helper.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryJacocoConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")
//            pluginManager.apply("com.android.library")

            val extension = extensions.getByType<LibraryExtension>()

            extension.buildTypes.configureEach {
                enableUnitTestCoverage = true
//                enableAndroidTestCoverage = true
            }

            configureJacoco(extensions.getByType<LibraryAndroidComponentsExtension>())
        }
    }
}


