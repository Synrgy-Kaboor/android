plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKsp()
}

android {
    namespace = AppConfig.namespace_data
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(project(Modules.common))

    implementation(Dependecies.datastore)
    implementation(Dependecies.room)
    implementation(Dependecies.room_ktx)
    ksp(Dependecies.room_compiler)
}