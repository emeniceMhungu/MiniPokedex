// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jacoco.report.aggregation)

}

dependencies {
    jacocoAggregation(project(":feature:pokedex:data"))
//    jacocoAggregation(project(":feature:pokedex:domain"))
    jacocoAggregation(project(":feature:pokedex:ui"))
    jacocoAggregation(project(":core:common"))
//    jacocoAggregation(project(":core:network"))
    jacocoAggregation(project(":lint"))

}

tasks.register("jacocoAggregationCoverageReport"){
    val jacocoAggregatedProjects = configurations.getByName("jacocoAggregation").allDependencies.map {
        (it as org.gradle.api.artifacts.ProjectDependency).dependencyProject
    }

    val coverageTasks = jacocoAggregatedProjects.flatMap { project ->
        project.tasks.matching { it.name.matches(Regex("create.*.CoverageReport")) }
    }
    dependsOn(coverageTasks)
}
