import com.android.build.api.dsl.ApplicationExtension
import com.assessment.minipokedex.helper.configureCommonTestingDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationTestConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply("com.android.application")
            extensions.configure<ApplicationExtension> {
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        animationsDisabled = true
                    }
                }
            }

            configureCommonTestingDependencies()
            /**
             * todo: consider adding common ui testing dependencies like
             *     androidTestImplementation(libs.androidx.espresso.core)
             *     androidTestImplementation(platform(libs.androidx.compose.bom))
             *     androidTestImplementation(libs.androidx.compose.ui.test.junit4)
             *     androidTestImplementation(libs.androidx.espresso.core)
             *     androidTestImplementation(libs.androidx.compose.ui.test.junit4)
             *     debugImplementation(libs.androidx.compose.ui.test.manifest)
             *
             *     NB: leaving them out as strategy is to temporarily ignore ui testing
             */
        }
    }
}