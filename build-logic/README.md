# Convention Plugins

The `build-logic` folder defines project-specific convention plugins, used to keep a single
source of truth for common module configurations.

This approach is heavily based on
[https://developer.squareup.com/blog/herding-elephants/](https://developer.squareup.com/blog/herding-elephants/)
and
[https://github.com/jjohannes/idiomatic-gradle](https://github.com/jjohannes/idiomatic-gradle).

By setting up convention plugins in `build-logic`, we can avoid duplicated build script setup,
messy `subproject` configurations, without the pitfalls of the `buildSrc` directory.

`build-logic` is an included build, as configured in the root
[`settings.gradle.kts`](../settings.gradle.kts).

Inside `build-logic` is a `convention` module, which defines a set of plugins that all normal
modules can use to configure themselves.

`build-logic` also includes a set of `Kotlin` files used to share logic between plugins themselves,
which is most useful for configuring Android components (libraries vs applications) with shared
code.

These plugins are *additive* and *composable*, and try to only accomplish a single responsibility.
Modules can then pick and choose the configurations they need.
If there is one-off logic for a module without shared code, it's preferable to define that directly
in the module's `build.gradle`, as opposed to creating a convention plugin with module-specific
setup.

Current list of convention plugins:

- Configures common Android options for all modules: 
  - [`minipokedex.android.application`](convention/src/main/kotlin/AndroidApplicationConventionPlugin.kt)
  - [`minipokedex.android.library`](convention/src/main/kotlin/AndroidLibraryConventionPlugin.kt) 
- Configures Jetpack Compose options: 
  - [`minipokedex.android.application.compose](convention/src/main/kotlin/AndroidApplicationComposeConventionPlugin.kt)
  - [`minipokedex.android.library.compose`](convention/src/main/kotlin/AndroidLibraryComposeConventionPlugin.kt)
- Configures test options:
  - [`minipokedex.android.application.test`](convention/src/main/kotlin/AndroidApplicationTestConventionPlugin.kt)
  - [`minipokedex.android.library.test`](convention/src/main/kotlin/AndroidLibraryTestConventionPlugin.kt)
  - [`minipokedex.kotlin.jvm.unit.test`](convention/src/main/kotlin/KotlinJvmUnitTestConventionPlugin.kt)

### Known Issues & Troubleshooting

#### Version Catalog Access in `build-logic`

**Symptom:**

When registering convention plugins in the `build-logic/convention/build.gradle.kts` file, you may encounter an `Unresolved reference` error for `libs`. 
This happens when trying to use the version catalog to define a plugin's `id`. For example, the following code will fail because `libs.xxx.get().pluginId` 
cannot be resolved in this context:
```kotlin
    register("androidApplication") { 
        id = libs.plugins.minipokedex.android.application.get().pluginId
//        id = "convention.android.application"
        implementationClass = "AndroidApplicationConventionPlugin"
    }
```