plugins {
  kotlin("jvm") version "1.3.60" apply false
}

subprojects {
  group = "io.github.spauck"

  repositories {
    mavenCentral()
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "6.0.1"
  distributionType = Wrapper.DistributionType.BIN
}
