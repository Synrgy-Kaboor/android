plugins {
    androidApp()
    kotlinAndroid()
    googleGmsServices()
    firebaseCrashlytics()
}

android {
    namespace = AppConfig.namespace_app
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = generateVersionCode()
        versionName = generateVersionName()

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    val env = readProperties(file("${rootProject.rootDir}/env.properties"))
    flavorDimensions.add(AppConfig.flavorDimensions)
    productFlavors {
        create(AppConfig.flavorDev){
            dimension = AppConfig.flavorDimensions
            applicationIdSuffix = AppConfig.applicationIdSuffixDev
        }
        create(AppConfig.flavorStaging){
            dimension = AppConfig.flavorDimensions
            applicationIdSuffix = AppConfig.applicationIdSuffixStaging
        }
        create(AppConfig.flavorProduction){
            dimension = AppConfig.flavorDimensions
            applicationIdSuffix = AppConfig.applicationIdSuffixProduction
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

    api(project(Modules.di))

    implementation(Dependecies.android_navigation_fragment)
    implementation(Dependecies.android_navigation_ui)
    implementation(Dependecies.flexbox)
    implementation(Dependecies.otpview)
    implementation(platform(Dependecies.firebase_bom))
    implementation(Dependecies.circle_image)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}