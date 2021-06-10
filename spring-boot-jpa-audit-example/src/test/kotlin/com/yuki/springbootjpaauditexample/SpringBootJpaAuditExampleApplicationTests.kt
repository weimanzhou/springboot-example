package com.yuki.springbootjpaauditexample

import com.yuki.springbootjpaauditexample.model.`do`.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = [SpringBootJpaAuditExampleApplication::class]
)
class SpringBootJpaAuditExampleApplicationTests {

    @Test
    fun contextLoads() {}

}
