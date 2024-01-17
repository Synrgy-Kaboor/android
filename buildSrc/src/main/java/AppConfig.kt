import java.io.File
import java.util.Properties

/**
 * Created by wahid on 12/26/2023.
 * Github github.com/wahidabd.
 */


object AppConfig {
    const val applicationId = "com.synrgy.kaboor"

    const val compileSdk = 34
    const val targetSdk = 34
    const val minSdk = 24
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val majorVersion = 0 // raise this number for major revamp/refactoring
    const val minorVersion = 0 // raise this number for feature release or regular release, max 99
    const val patchVersion = 1 // raise this number for hotfix release, max 99
    const val bumpVersion = 0 // raise this number for daily build (firebase), max 99
    const val preRelease =
        "Beta" // leave this empty for production release, otherwise use "Beta", "Alpha", etc

    const val namespace_app = "com.synrgy.kaboor"
    const val namespace_data = "com.synrgy.data"
    const val namespace_domain = "com.synrgy.domain"
    const val namespace_di = "com.synrgy.di"
    const val namespace_common = "com.synrgy.common"

    const val flavorDimensions = "env"
    const val flavorDev = "dev"
    const val applicationIdSuffixDev = ".dev"

    const val flavorStaging = "staging"
    const val applicationIdSuffixStaging = ".staging"

    const val flavorProduction = "production"
    const val applicationIdSuffixProduction = ""
}

fun generateVersionCode(): Int {
    with(AppConfig) {
        return (majorVersion * 1000000) + (minorVersion * 10000) + (patchVersion * 100) + bumpVersion
    }
}

fun generateReleaseVersionCode(): Int {
    with(AppConfig) {
        return (majorVersion * 1000000) + (minorVersion * 10000) + (patchVersion * 100)
    }
}

fun generateVersionName(): String {
    with(AppConfig) {
        var versionName = "$majorVersion.$minorVersion.$patchVersion"
        if (preRelease.isNotEmpty()) {
            versionName = "$versionName-$preRelease"
        }
        return versionName
    }
}

fun readProperties(properties: File) = Properties().apply {
    properties.inputStream().use { load(it) }
}