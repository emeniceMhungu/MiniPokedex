import androidx.room.gradle.RoomExtension
import com.assessment.minipokedex.helper.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("androidx.room")
            pluginManager.apply("com.google.devtools.ksp")

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }

            extensions.configure<RoomExtension> {
                /**
                 * This schemas directory contains a schema file for each version of the Room database.
                 * The schema file is used to export the database structure to a JSON file, which can be
                 * used to migrate the database from one version to another.
                 */
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                "api"(libs.findLibrary("androidx.room.runtime").get())
                "implementation"(libs.findLibrary("androidx.room.ktx").get())
                "ksp"(libs.findLibrary("androidx.room.compiler").get())
            }

        }
    }
}