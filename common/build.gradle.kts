plugins {
    androidLibrary()
    kotlinAndroid()
    googleGmsServices()
    firebaseCrashlytics()
    kotlinParcelize()
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar", "*.jar"))))

    api(Dependecies.core)
    api(Dependecies.junit)
    api(Dependecies.junit_ext)
    api(Dependecies.espresso_core)
    api(Dependecies.appcompat)
    api(Dependecies.material)
    api(Dependecies.constraintlayout)
    api(Dependecies.onelib)
    api(Dependecies.serialzation)
    api(Dependecies.firebase_analytics)
    api(Dependecies.firebase_crashlytics)
    api(Dependecies.multi_state_view)

    debugApi(Dependecies.chucker)
    releaseApi(Dependecies.chucker_no_op)
    implementation(Dependecies.lottie)
}
