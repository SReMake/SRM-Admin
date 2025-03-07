package com.SReMake

import org.babyfish.jimmer.client.EnableImplicitApi
import org.babyfish.jimmer.sql.EnableDtoGeneration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDtoGeneration
@EnableImplicitApi
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
