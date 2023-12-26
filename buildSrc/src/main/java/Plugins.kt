import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * Created by wahid on 12/26/2023.
 * Github github.com/wahidabd.
 */


fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    id("org.jetbrains.kotlin.android")

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp")