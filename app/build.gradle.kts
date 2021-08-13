plugins {
  id(GradlePluginId.ANDROID_APPLICATION)
  id(GradlePluginId.KOTLIN_ANDROID)
  id(GradlePluginId.KOTLIN_KAPT)
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
      buildConfigField("String", "API_URL", "\"https://recruitment-task.futuremind.dev/\"")
      proguardFiles("proguard-android.txt", "proguard-rules.pro")
    }

    getByName(BuildType.DEBUG) {
      buildConfigField("String", "API_URL", "\"https://recruitment-task.futuremind.dev/\"")
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
  buildFeatures {
    viewBinding = true
    dataBinding = true
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
  api(libs.bundles.bindingLib)
  api(libs.moshi)

  kapt(libs.room.compiler)
  kapt(libs.hilt.compiler)
  kapt(libs.moshiCodegen)

  testImplementation(libs.bundles.test)

  testRuntimeOnly(libs.junit.jupiter.engine)

  implementation(project(":data"))
  implementation(project(":domain"))
  implementation(project(":interactors"))
  implementation(project(":ui"))
}
