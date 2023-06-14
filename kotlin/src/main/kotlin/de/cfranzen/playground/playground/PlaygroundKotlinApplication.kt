package de.cfranzen.playground.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundKotlinApplication

fun main(args: Array<String>) {
	runApplication<PlaygroundKotlinApplication>(*args)
}
