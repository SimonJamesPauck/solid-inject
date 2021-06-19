plugins {
  kotlin("jvm") version "1.5.10" apply false
}

subprojects {
  group = "io.github.spauck"

  repositories {
    mavenCentral()
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "7.1"
  distributionType = Wrapper.DistributionType.BIN
}
