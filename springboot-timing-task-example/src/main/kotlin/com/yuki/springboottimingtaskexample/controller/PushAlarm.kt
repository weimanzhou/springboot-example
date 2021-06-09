package com.yuki.springboottimingtaskexample.controller

import com.yuki.springboottimingtaskexample.config.WebSocketServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
@Order(1)
class PushAlarm: ApplicationRunner {

    @Autowired
    val webSocketServer: WebSocketServer? = null

    override fun run(args: ApplicationArguments?) {

    }

    @Scheduled(fixedRate = 1000)
    fun scheduleTask() {
        println(LocalDateTime.now())
        println(WebSocketServer.map.size)
        val iterator = WebSocketServer.map.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            webSocketServer!!.sendMessage(entry.key, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        }
    }

}
