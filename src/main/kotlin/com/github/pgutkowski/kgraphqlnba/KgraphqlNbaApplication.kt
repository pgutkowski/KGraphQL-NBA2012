package com.github.pgutkowski.kgraphqlnba

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KgraphqlNbaApplication

fun main(args: Array<String>) {
    SpringApplication.run(KgraphqlNbaApplication::class.java, *args)
}
