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
            buildConfigField("String", "base_url", env.getProperty("base_url_dev") as String)
        }
        create(AppConfig.flavorStaging){
            dimension = AppConfig.flavorDimensions
            applicationIdSuffix = AppConfig.applicationIdSuffixStaging
            buildConfigField("String", "base_url", env.getProperty("base_url_staging") as String)
        }
        create(AppConfig.flavorProduction){
            dimension = AppConfig.flavorDimensions
            applicationIdSuffix = AppConfig.applicationIdSuffixProduction
            buildConfigField("String", "base_url", env.getProperty("base_url_production") as String)
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
}