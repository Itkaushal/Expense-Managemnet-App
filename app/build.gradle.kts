plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.kaushlendraprajapati.roomdbpractice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kaushlendraprajapati.roomdbpractice"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    // room data base dependency...
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation(libs.swiperefreshlayout)

    annotationProcessor ("androidx.room:room-compiler:2.6.1")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}