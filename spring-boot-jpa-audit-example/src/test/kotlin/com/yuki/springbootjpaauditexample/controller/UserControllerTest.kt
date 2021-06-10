package com.yuki.springbootjpaauditexample.controller

import com.yuki.springbootjpaauditexample.SpringBootJpaAuditExampleApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
//@WebMvcTest
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = [SpringBootJpaAuditExampleApplication::class]
)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

//    @MockBean
//    private val userVehicleService: UserVehicleService? = null

    @Test
    @Throws(Exception::class)
    fun testExample() {
//        given(userVehicleService.getVehicleDetails("sboot"))
//                .willReturn(VehicleDetails("Honda", "Civic"))
        mvc!!.perform(get("http://localhost:8080/user")
                            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

}