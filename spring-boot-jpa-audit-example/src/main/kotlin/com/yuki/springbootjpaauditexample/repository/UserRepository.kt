package com.yuki.springbootjpaauditexample.repository

import com.yuki.springbootjpaauditexample.model.`do`.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
interface UserRepository: JpaRepository<User, Long> {}
