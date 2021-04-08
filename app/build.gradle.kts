plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildTools)


    defaultConfig {
        applicationId = "nobleminsu.kakaoimagesearch"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
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
    flavorDimensions("default")
    productFlavors {
        create("dev") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            addBuildConfigField(
                com.android.builder.internal.ClassFieldImpl(
                    "String",
                    "BASE_URL",
                    "\"dapi.kakao.com\""
                )
            )
        }
        create("prod") {
            dimension = "default"
            addBuildConfigField(
                com.android.builder.internal.ClassFieldImpl(
                    "String",
                    "BASE_URL",
                    "\"dapi.kakao.com\""
                )
            )
        }
    }
}

dependencies {
    implementation(AppDependencies.implementations)
    testImplementation(AppDependencies.testImplementations)
    androidTestImplementation(AppDependencies.androidTestImplementations)
    kapt(AppDependencies.kapts)
}