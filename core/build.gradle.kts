import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("maven-publish")
}

version = "0.1.0"

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  testImplementation("org.assertj:assertj-core:3.12.2")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
  testImplementation("org.json:json:20190722")

  testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}
val sourcesJar by tasks.creating(Jar::class) {
  archiveClassifier.set("sources")
  from(sourceSets.main.get().allSource)
}
val javadocJar by tasks.creating(Jar::class) {
  dependsOn.add(tasks.javadoc)
  archiveClassifier.set("javadoc")
  from(tasks.javadoc)
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      artifact(sourcesJar)
      artifact(javadocJar)
      artifact(tasks.jar.get())
    }
  }
}
