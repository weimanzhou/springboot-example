package com.yuki.springbootjpaauditexample

import com.yuki.springbootjpaauditexample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class SpringBootJpaAuditExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootJpaAuditExampleApplication>(*args)
}
