plugins {
  id(GradlePluginId.ANDROID_APPLICATION)
  id(GradlePluginId.KOTLIN_ANDROID)
  id(GradlePluginId.KOTLIN_KAPT)
  id(GradlePluginId.KTLINT_GRADLE)
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
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}


dependencies {
  implementation(libs.bundles.kotlin)
  api(libs.bundles.retrofit)
  api(libs.bundles.kotlin)
  api(libs.bundles.ktx)
  api(libs.bundles.room)
  api(libs.coroutines)
  api(libs.joda)
  api(libs.hilt.android)
  implementation(project(mapOf("path" to ":domain")))

  kapt(libs.room.compiler)
  kapt(libs.hilt.compiler)

  project(":domain")
}