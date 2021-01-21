buildscript {
  repositories.addAll(rootProject.buildscript.repositories)
}

plugins {
  groovy
  id("com.github.ben-manes.versions")
  id("org.springframework.boot")
}

springBoot {
  buildInfo()
}

normalization {
  runtimeClasspath {
    ignore("**/build-info.properties")
  }
}

tasks {
  bootJar {
    archiveClassifier.set("boot")
    enabled = true
  }

  jar {
    enabled = true
  }
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))

  implementation("org.codehaus.groovy:groovy:${rootProject.extra["groovyVersion"]}")

  implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda:${rootProject.extra["jacksonVersion"]}")

  implementation("joda-time:joda-time:${rootProject.extra["jodaTimeVersion"]}")
  implementation("com.expediagroup", "graphql-kotlin-schema-generator", "3.6.4")

  implementation("com.github.ben-manes.caffeine:caffeine:${rootProject.extra["caffeineVersion"]}")

  implementation("org.springframework.boot:spring-boot-starter-web:${rootProject.extra["springBootVersion"]}")
  implementation("org.springframework.boot:spring-boot-starter-actuator:${rootProject.extra["springBootVersion"]}")

  implementation("org.springframework:spring-beans:${rootProject.extra["springVersion"]}")
  implementation("org.springframework:spring-context:${rootProject.extra["springVersion"]}")
  implementation("org.springframework:spring-context-support:${rootProject.extra["springVersion"]}")
  implementation("org.springframework:spring-web:${rootProject.extra["springVersion"]}")
  implementation("org.springframework:spring-webmvc:${rootProject.extra["springVersion"]}")
  implementation("javax.validation:validation-api:2.0.1.Final")

  implementation("org.springframework.security:spring-security-core:${rootProject.extra["springSecurityVersion"]}")
  implementation("org.springframework.security:spring-security-config:${rootProject.extra["springSecurityVersion"]}")
  implementation("org.springframework.security:spring-security-web:${rootProject.extra["springSecurityVersion"]}")

  implementation("org.slf4j:slf4j-api:${rootProject.extra["slf4jVersion"]}")
  runtimeOnly("org.slf4j:log4j-over-slf4j:${rootProject.extra["slf4jVersion"]}")
  runtimeOnly("ch.qos.logback:logback-classic:${rootProject.extra["logbackVersion"]}")
  runtimeOnly("org.codehaus.janino:janino:${rootProject.extra["janinoVersion"]}")
  runtimeOnly("ch.qos.logback:logback-access:${rootProject.extra["logbackVersion"]}")
  runtimeOnly("net.logstash.logback:logstash-logback-encoder:${rootProject.extra["logstashLogbackEncoderVersion"]}")


  testImplementation("org.springframework.boot:spring-boot-test:${rootProject.extra["springBootVersion"]}")
  testImplementation("org.springframework.security:spring-security-test:${rootProject.extra["springSecurityVersion"]}")
  testImplementation("io.mockk:mockk:${rootProject.extra["mockkVersion"]}")
  testImplementation("com.ninja-squad:springmockk:2.0.2")
  testImplementation("io.kotest:kotest-extensions-spring:${rootProject.extra["kotestVersion"]}")
  testImplementation("io.kotest:kotest-framework-engine-jvm:${rootProject.extra["kotestVersion"]}")
  testImplementation("io.kotest:kotest-property-jvm:${rootProject.extra["kotestVersion"]}")
  testImplementation("io.kotest:kotest-runner-junit5-jvm:${rootProject.extra["kotestVersion"]}")
}
