package com.SReMake

import org.babyfish.jimmer.client.EnableImplicitApi
import org.babyfish.jimmer.sql.EnableDtoGeneration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableDtoGeneration
@EnableImplicitApi
@EnableScheduling
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
