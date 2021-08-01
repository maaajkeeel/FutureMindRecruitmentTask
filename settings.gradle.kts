rootProject.buildFileName = "build.gradle.kts"

enableFeaturePreview("ONE_LOCKFILE_PER_PROJECT")
enableFeaturePreview("VERSION_CATALOGS")

include(":app")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }

  plugins {
    val agpVersion: String by settings
    id("com.android.application") version agpVersion
    id("com.android.library") version agpVersion
    id("com.android.dynamic-feature") version agpVersion

    val kotlinVersion: String by settings
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.android") version kotlinVersion

    val navigationVersion: String by settings
    id("androidx.navigation.safeargs.kotlin") version navigationVersion

    val detektVersion: String by settings
    id("io.gitlab.arturbosch.detekt") version detektVersion

    val ktlintVersion: String by settings
    id("org.jlleitschuh.gradle.ktlint") version ktlintVersion

    val androidJUnit5Version: String by settings
    id("de.mannodermaus.android-junit5") version androidJUnit5Version

    val hiltVersion: String by settings
    id("dagger.hilt.android.plugin") version hiltVersion
  }

  resolutionStrategy {
    eachPlugin {

      when (requested.id.id) {
        "com.android.application",
        "com.android.library",
        "com.android.dynamic-feature" -> {
          val agpCoordinates: String by settings
          useModule(agpCoordinates)
        }
        "androidx.navigation.safeargs.kotlin" -> {
          val navigationCoordinates: String by settings
          useModule(navigationCoordinates)
        }
        "de.mannodermaus.android-junit5" -> {
          val androidJnit5Coordinates: String by settings
          useModule(androidJnit5Coordinates) // navigationVersion
        }
        "dagger.hilt.android.plugin" -> {
          val hiltCoordinates: String by settings
          useModule(hiltCoordinates)
        }
      }
    }
  }
}

@Suppress("detekt.StringLiteralDuplication")
dependencyResolutionManagement {
  versionCatalogs {
    create("libs") {

      val kotlinVersion: String by settings
      version("kotlin", kotlinVersion)

      alias("kotlin-reflect").to("org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
      version("coroutines", "1.+")
      alias("coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef("coroutines")
      bundle("kotlin", listOf("kotlin-reflect", "coroutines"))

      version("retrofit", "2.+")
      alias("retrofit-core").to("com.squareup.retrofit2", "retrofit").versionRef("retrofit")
      alias("converter-moshi").to("com.squareup.retrofit2", "converter-moshi").versionRef("retrofit")
      bundle("retrofit", listOf("retrofit-core", "converter-moshi"))

      alias("moshiCodegen").to("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
      alias("autoService").to("com.google.auto.service:auto-service-annotations:1.0")

      version("okhttp", "4.+")
      alias("okhttp-okhttp").to("com.squareup.okhttp3", "okhttp").versionRef("okhttp")
      alias("okhttp-interceptor").to("com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
      bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))


      version("stetho", "1.5.0")
      alias("stetho-core").to("com.facebook.stetho", "stetho").versionRef("stetho")
      alias("stetho-okhttp3").to("com.facebook.stetho", "stetho-okhttp3").versionRef("stetho")
      bundle("stetho", listOf("stetho-core", "stetho-okhttp3"))

      alias("timber").to("com.jakewharton.timber:timber:4.+")
      alias("joda").to("net.danlew:android.joda:2.+")
      alias("constraintlayout").to("androidx.constraintlayout:constraintlayout:2.+")
      alias("coordinatorlayout").to("androidx.coordinatorlayout:coordinatorlayout:1.+")
      alias("appcompat").to("androidx.appcompat:appcompat:1.+")
      alias("recyclerview").to("androidx.recyclerview:recyclerview:1.+")
      alias("material").to("com.google.android.material:material:1.+")
      alias("play-core").to("com.google.android.play:core:1.+")

      alias("core-ktx").to("androidx.core:core-ktx:1.+")
      alias("fragment-ktx").to("androidx.fragment:fragment-ktx:1.+")
      bundle("ktx", listOf("core-ktx", "fragment-ktx"))

      version("lifecycle", "2.+")
      alias("viewmodel-ktx").to("androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
      alias("livedata-ktx").to("androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
      alias("lifecycle-common").to("androidx.lifecycle", "lifecycle-common-java8").versionRef("lifecycle")
      bundle("lifecycle", listOf("viewmodel-ktx", "livedata-ktx", "lifecycle-common"))

      val navigationVersion: String by settings
      version("navigation", navigationVersion)
      alias("navigation-fragment").to("androidx.navigation", "navigation-fragment-ktx").versionRef("navigation")
      alias("navigation-dynamic")
        .to("androidx.navigation", "navigation-dynamic-features-fragment")
        .versionRef("navigation")
      alias("navigation-ui-ktx").to("androidx.navigation", "navigation-ui-ktx").versionRef("navigation")
      bundle("navigation", listOf("navigation-fragment", "navigation-dynamic", "navigation-ui-ktx"))

      version("room", "2.+")
      alias("room-ktx").to("androidx.room", "room-ktx").versionRef("room")
      alias("room-runtime").to("androidx.room", "room-runtime").versionRef("room")
      bundle("room", listOf("room-ktx", "room-runtime"))

      alias("room.compiler").to("androidx.room", "room-compiler").versionRef("room")

      val hiltVersion: String by settings
      version("hiltVer", hiltVersion)
      alias("hilt-android").to("com.google.dagger", "hilt-android").versionRef("hiltVer")
      alias("hilt-compiler").to("com.google.dagger", "hilt-android-compiler").versionRef("hiltVer")

      alias("test-coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutines")

      alias("test-runner").to("androidx.test:runner:1.+")
      alias("espresso").to("androidx.test.espresso:espresso-core:3.+")
      alias("mockk").to("io.mockk:mockk:1.+")
      alias("arch").to("androidx.arch.core:core-testing:2.+")

      version("junit", "5.+")
      alias("junit-jupiter-api").to("org.junit.jupiter", "junit-jupiter-api").versionRef("junit")

      bundle(
        "test",
        listOf(
          "test-coroutines",
          "test-runner",
          "espresso",
          "mockk",
          "arch",
          "junit-jupiter-api"
        )
      )

      alias("junit-jupiter-engine").to("org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
    }
  }
}
include(":data")
include(":domain")
include(":interactors")
