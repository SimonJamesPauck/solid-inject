plugins {
  kotlin("jvm") version "1.3.60" apply false
}

subprojects {
  group = "solid.inject"

  repositories {
    mavenCentral()
  }
}
