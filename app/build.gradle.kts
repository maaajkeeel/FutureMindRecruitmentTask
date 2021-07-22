plugins {
  id(GradlePluginId.ANDROID_APPLICATION)
  id(GradlePluginId.KOTLIN_ANDROID) // or kotlin("android") or id 'kotlin-android'
  id(GradlePluginId.KOTLIN_KAPT) // or kotlin("kapt")
  id(GradlePluginId.KTLINT_GRADLE)
  id(GradlePluginId.SAFE_ARGS)
  id(GradlePluginId.ANDROID_JUNIT_5)
  id(GradlePluginId.HILT)
}

android {
  compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

  defaultConfig {
    applicationId = AndroidConfig.ID
    minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
    targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
    buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

    versionCode = AndroidConfig.VERSION_CODE
    versionName = AndroidConfig.VERSION_NAME
    testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
  }

  buildTypes {
    getByName(BuildType.RELEASE) {
      isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
      proguardFiles("proguard-android.txt", "proguard-rules.pro")
    }

    getByName(BuildType.DEBUG) {
      isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
    }

    testOptions {
      unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }

    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
  }
  buildFeatures.viewBinding = true
  buildFeatures.dataBinding = true

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}
dependencies {
  // Gradle 7 introduces version catalogs - a new way for sharing dependency versions across projects.
  // Dependencies are defined in gradle.settings.kts file.
  // False positive cannot access class (fixed in InteliJ IDEA 2021.1 EAP 1 afair)
  api(libs.bundles.kotlin)
  api(libs.bundles.stetho)
  api(libs.bundles.retrofit)
  api(libs.bundles.okhttp)
  api(libs.play.core)
  api(libs.bundles.ktx)
  api(libs.bundles.navigation)
  api(libs.bundles.lifecycle)
  api(libs.bundles.room)
  api(libs.timber)
  api(libs.constraintlayout)
  api(libs.coordinatorlayout)
  api(libs.appcompat)
  api(libs.recyclerview)
  api(libs.material)
  api(libs.coroutines)
  api(libs.joda)
  api(libs.hilt.android)

  kapt(libs.room.compiler)
  kapt(libs.hilt.compiler)

  testImplementation(libs.bundles.test)

  testRuntimeOnly(libs.junit.jupiter.engine)
}
