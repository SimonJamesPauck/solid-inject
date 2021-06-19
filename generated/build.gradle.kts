import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
}

version = "0.1-SNAPSHOT"

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("com.squareup:kotlinpoet:1.3.0")
  implementation(project(":solid-inject-core"))

  testImplementation("org.assertj:assertj-core:3.22.0")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}
