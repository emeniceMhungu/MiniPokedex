import com.android.build.api.dsl.LibraryExtension
import com.assessment.minipokedex.helper.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * It's important to call this convention after android library or application and ksp plugins
 * have been applied. Failure to follow that order will break build as it assumes those two to
 * have been applied.
 */
class NetworkingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Retrofit & OkHttp
                "api"(libs.findLibrary("retrofit.core").get())
                "api"(libs.findLibrary("retrofit.converter.moshi").get())
                "api"(libs.findLibrary("okhttp.core").get())
                "implementation"(libs.findLibrary("okhttp.logging").get())

                /**
                 * Moshi Dependencies for JSON parsing
                 */
                "api"(libs.findLibrary("moshi.kotlin").get())
                "ksp"(libs.findLibrary("moshi.codegen").get())

                "api"(libs.findLibrary("kotlin.reflect").get())

                /**
                 * The Slack "EitherNet" library is used to provide a type-safe,
                 * resilient framework for handling structured network API
                 * responses and failures within Android applications.
                 */
                "api"(libs.findLibrary("slack.eithernet").get())
//                "api"(libs.findLibrary("slack.eithernet.integration").get())
                "testImplementation"(libs.findLibrary("slack.eithernet").get())
            }
        }
    }
}