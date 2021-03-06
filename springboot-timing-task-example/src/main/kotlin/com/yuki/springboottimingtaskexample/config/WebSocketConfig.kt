package com.yuki.springboottimingtaskexample.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.server.standard.ServerEndpointExporter

/**
 *
 * @Description: socket配置类,往 spring 容器中注入ServerEndpointExporter实例
 *
 */
@Configuration
class WebSocketConfig {

    @Bean
    fun serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter();
    }

}
