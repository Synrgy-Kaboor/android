plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = AppConfig.namespace_common
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))

    api(Dependecies.core)
    api(Dependecies.junit)
    api(Dependecies.junit_ext)
    api(Dependecies.espresso_core)

    debugApi(Dependecies.chucker)
    releaseApi(Dependecies.chucker_no_op)

    api(Dependecies.onelib)
}
