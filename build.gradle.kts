plugins {
  kotlin("jvm") version "1.7.0" apply false
}

subprojects {
  group = "io.github.spauck"

  repositories {
    mavenCentral()
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "7.4.2"
  distributionType = Wrapper.DistributionType.BIN
}
