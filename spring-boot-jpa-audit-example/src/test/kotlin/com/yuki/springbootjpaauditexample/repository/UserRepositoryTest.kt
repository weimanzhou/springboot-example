package com.yuki.springbootjpaauditexample.repository

import com.yuki.springbootjpaauditexample.SpringBootJpaAuditExampleApplication
import com.yuki.springbootjpaauditexample.aware.UserAware
import com.yuki.springbootjpaauditexample.model.`do`.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
//@ContextConfiguration(classes = [SpringBootJpaAuditExampleApplication::class])
//@ComponentScan(basePackages = ["com.yuki.springbootjpaauditexample.aware"])
@Import(*[UserAware::class])
//@SpringBootTest
//@AutoConfigureDataJpa
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun save() {
        val user = User()
        user.username = "TAKO"
        user.password = "TAKO"

        userRepository.save(user)

        println(userRepository.findAll())
        Thread.sleep(2000)

        user.username = "TAKO2"
        userRepository.save(user)
        println(userRepository.findAll())

    }

}