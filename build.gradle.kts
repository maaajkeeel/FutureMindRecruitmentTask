plugins {
  id(GradlePluginId.DETEKT)
  id(GradlePluginId.KTLINT_GRADLE)
  id(GradlePluginId.ANDROID_JUNIT_5) apply false
  id(GradlePluginId.KOTLIN_ANDROID) apply false
  id(GradlePluginId.ANDROID_APPLICATION) apply false
  id(GradlePluginId.ANDROID_LIBRARY) apply false
  id(GradlePluginId.SAFE_ARGS) apply false
  id(GradlePluginId.HILT) apply false
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
  apply(plugin = GradlePluginId.KTLINT_GRADLE)
  ktlint {
    version.set("0.40.0")
    verbose.set(true)
    android.set(true)

    reporters {
      reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }

    filter {
      exclude { element -> element.file.path.contains("generated/") }
    }
  }
  dependencyLocking {
    lockAllConfigurations()
  }
}

tasks.register("resolveAndLockAll") {
  doFirst {
    require(gradle.startParameter.isWriteDependencyLocks)
  }
  doLast {
    configurations.filter {
      // Add any custom filtering on the configurations to be resolved
      it.isCanBeResolved
    }.forEach { it.resolve() }
  }
}