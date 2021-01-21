package de.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan

@EnableCaching
@SpringBootApplication
class SampleApplication

fun main(args: Array<String>) {
  runApplication<SampleApplication>(*args)
}
