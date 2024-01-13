import org.gradle.kotlin.dsl.kotlin
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

fun PluginDependenciesSpec.android(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.androidKotlin(): PluginDependencySpec =
    id("kotlin-android")

fun PluginDependenciesSpec.kotlinKsp(): PluginDependencySpec =
    id("com.google.devtools.ksp")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpec.kotlinSerialization(): PluginDependencySpec =
    kotlin("plugin.serialization")