import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
  id(GradlePluginId.ANDROID_LIBRARY)
  id(GradlePluginId.KOTLIN_ANDROID)
  id(GradlePluginId.ANDROID_JUNIT_5)
  id(GradlePluginId.KOTLIN_KAPT)
  id(GradlePluginId.HILT)
  kotlin(GradlePluginId.ANDROID_EXTENSION)
}

android {
  compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

  defaultConfig {
    minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
    targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

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
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  testOptions {
    unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
  }

  packagingOptions {
    // May not be needed after updating to AGP 4.x - check
    exclude("META-INF/AL2.0")
    exclude("META-INF/LGPL2.1")
  }
}

dependencies {
  // implementation configuration is used here (instead of testImplementation) because this module is added as
  // testImplementation dependency inside other modules. Using implementation allows to write tests for test
  // utilities.
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
  api(libs.moshi)

  kapt(libs.room.compiler)
  kapt(libs.hilt.compiler)
  kapt(libs.moshiCodegen)

  testImplementation(libs.bundles.test)

  testRuntimeOnly(libs.junit.jupiter.engine)

  implementation(project(":domain"))

  runtimeOnly(libs.junit.jupiter.engine)
}
