package com.yuki.springboottimingtaskexample.config

import org.springframework.stereotype.Component
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

@Component
@ServerEndpoint(value = "/ws/{userName}")
class WebSocketServer {

    companion object {
        var map = mutableMapOf<Session, String>()
    }

    @OnOpen
    fun onOpen(@PathParam("userName") userName: String, session: Session) {
        println(userName)
        map[session] = userName
        println(map.size)
        println("OnOpen()方法被执行...")
    }

    @OnClose
    fun onClose(session: Session) {
        if (map.containsKey(session)) {
            map.remove(session)
        }
        println("WebSocketServer.onClose")
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        sendMessage(session, message)
        println(message)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        println("error")
    }

    /**
     *
     * 推送数据的方法
     *
     *
     *
     */
    fun sendMessage(session: Session, message: String) {
        print(session.isOpen)
        session.basicRemote.sendText(message)
    }

}
