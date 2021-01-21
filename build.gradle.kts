buildscript {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    jcenter()
  }

  extra.apply {
    set("caffeineVersion", "2.8.6")
    set("cglibVersion", "3.3.0")
    set("groovyVersion", "2.5.13")
    set("jacksonVersion", "2.11.2")
    set("janinoVersion", "3.1.2")
    set("jodaTimeVersion", "2.10.6")
    set("kotlinVersion", "1.4.21") //remember to also adapt the plugin section
    set("kotestVersion", "4.3.0")
    set("logbackVersion", "1.2.3")
    set("logstashLogbackEncoderVersion", "6.4")
    set("mockkVersion", "1.10.2")
    set("slf4jVersion", "1.7.30")
    set("spockVersion", "1.3-groovy-2.5")
    set("springVersion", "5.2.8.RELEASE")
    set("springBootVersion", "2.3.7.RELEASE") //remember to also adapt the plugin section
    set("springSecurityVersion", "5.3.4.RELEASE")
  }
}

val javaVersion = JavaVersion.VERSION_14

val dependencyVersions = listOf(
    "commons-codec:commons-codec:1.14",
    "io.github.classgraph:classgraph:4.8.88",
    "io.projectreactor:reactor-core:3.3.10.RELEASE",
    "joda-time:joda-time:${extra["jodaTimeVersion"]}",
    "net.bytebuddy:byte-buddy:1.10.17",
    "net.logstash.logback:logstash-logback-encoder:${extra["logstashLogbackEncoderVersion"]}",
    "org.hamcrest:hamcrest-core:2.2",
    "org.reactivestreams:reactive-streams:1.0.3"
)

val dependencyGroupVersions = mapOf(
    "com.fasterxml.jackson.core" to extra["jacksonVersion"] as String,
    "com.fasterxml.jackson.datatype" to extra["jacksonVersion"] as String,
    "io.micrometer" to "1.5.4",
    "io.mockk" to extra["mockkVersion"] as String,
    "io.prometheus" to "0.9.0",
    "org.codehaus.groovy" to extra["groovyVersion"] as String,
    "org.jetbrains.kotlin" to extra["kotlinVersion"] as String,
    "org.jetbrains.kotlinx" to "1.3.9",
    "org.slf4j" to extra["slf4jVersion"] as String,
    "org.springframework" to extra["springVersion"] as String,
    "org.springframework.boot" to extra["springBootVersion"] as String,
    "org.springframework.security" to extra["springSecurityVersion"] as String
)

plugins {
  val kotlinVersion = "1.4.21"
  kotlin("jvm") version kotlinVersion apply false
  kotlin("plugin.spring") version kotlinVersion apply false
  kotlin("plugin.allopen") version kotlinVersion apply false

  id("com.github.ben-manes.versions") version "0.36.0"
  id("org.springframework.boot") version "2.3.7.RELEASE" apply false

  id("io.kotest") version "0.2.6"
}

allprojects {
  group = "de.sample"
}

subprojects {
  project.apply(plugin = "java")
  project.apply(plugin = "maven-publish")
  project.apply(plugin = "org.jetbrains.kotlin.jvm")
  project.apply(plugin = "org.jetbrains.kotlin.plugin.spring")
  project.apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
  project.apply(plugin = "io.kotest")

  repositories {
    mavenCentral ()
  }

  configurations {
    all {
      exclude(module = "log4j")
      exclude(module = "servlet-api")
      resolutionStrategy {
        failOnVersionConflict()
        force(dependencyVersions)
        eachDependency {
          val forcedVersion = dependencyGroupVersions[requested.group]
          if (forcedVersion != null) {
            useVersion(forcedVersion)
          }
        }
        cacheDynamicVersionsFor(0, "seconds")
      }
    }
  }

  configure<JavaPluginConvention> {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
  }

  tasks {
    withType<Test> {
      systemProperties(System.getProperties().map { it.key.toString() to it.value }.toMap())
      useJUnitPlatform()
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
        jvmTarget = javaVersion.toString()
        freeCompilerArgs = listOf("-Xjsr305=strict")
      }
    }
  }
}
