package com.yuki.springboottimingtaskexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringbootTimingTaskExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringbootTimingTaskExampleApplication>(*args)
}
