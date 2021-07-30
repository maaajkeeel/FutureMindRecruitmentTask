plugins {
  id("kotlin")
}

dependencies {
  // implementation configuration is used here (instead of testImplementation) because this module is added as
  // testImplementation dependency inside other modules. Using implementation allows to write tests for test
  // utilities.
  implementation(libs.bundles.kotlin)
}