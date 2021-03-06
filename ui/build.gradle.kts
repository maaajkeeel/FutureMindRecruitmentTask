plugins {
  id(GradlePluginId.ANDROID_LIBRARY)
  id(GradlePluginId.KOTLIN_ANDROID)
  id(GradlePluginId.KOTLIN_KAPT)
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

  buildFeatures {
    dataBinding = true
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }

  testOptions {
    unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
  }

  packagingOptions {
    exclude("META-INF/AL2.0")
    exclude("META-INF/LGPL2.1")
    exclude("META-INF/LICENSE")
    exclude("META-INF/LICENSE-notice")
  }
}

dependencies {
  implementation(libs.bundles.kotlin)
  api(libs.constraintlayout)
  api(libs.coordinatorlayout)
  api(libs.cardview)
  api(libs.appcompat)
  api(libs.recyclerview)
  api(libs.material)
  api(libs.picasso)
  api(libs.bundles.bindingLib)
  api(libs.joda)
}