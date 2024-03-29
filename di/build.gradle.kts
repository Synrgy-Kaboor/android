plugins {
    androidLibrary()
    kotlinAndroid()
}

android {
    namespace = AppConfig.namespace_di
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

    val env = readProperties(file("${rootProject.rootDir}/env.properties"))
    flavorDimensions.add(AppConfig.flavorDimensions)
    productFlavors {
        create(AppConfig.flavorDev){
            dimension = AppConfig.flavorDimensions
            buildConfigField("String", "base_url", env.getProperty("base_url_dev") as String)
            buildConfigField("String", "node_base_url", env.getProperty("node_base_url_dev") as String)
        }
        create(AppConfig.flavorStaging){
            dimension = AppConfig.flavorDimensions
            buildConfigField("String", "base_url", env.getProperty("base_url_staging") as String)
            buildConfigField("String", "node_base_url", env.getProperty("node_base_url_staging") as String)
        }
        create(AppConfig.flavorProduction){
            dimension = AppConfig.flavorDimensions
            buildConfigField("String", "base_url", env.getProperty("base_url_production") as String)
            buildConfigField("String", "node_base_url", env.getProperty("node_base_url_production") as String)
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
    api(project(Modules.domain))
}