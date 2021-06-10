package com.yuki.springbootjpaauditexample.controller

import com.yuki.springbootjpaauditexample.model.`do`.User
import com.yuki.springbootjpaauditexample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository

    @RequestMapping("/user")
    fun user(): User {
        val user =  User()
        userRepository.save(user)
        return user
    }

}