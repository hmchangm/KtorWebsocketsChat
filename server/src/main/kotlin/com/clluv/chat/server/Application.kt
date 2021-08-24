package com.clluv.chat.server

import com.clluv.chat.server.service.ChatService
import io.ktor.application.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(WebSockets)
    routing {
        // A thread-safe collection of Collections.
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/chat") {
            val thisConnection = Connection(this)
            connections += thisConnection
            println("[${thisConnection.name} joins!]")
            try {
                send("[You are connected with the name ${thisConnection.name}! There are ${connections.count()} users here.]")
                for (frame in incoming) {
                    val allUsernames = connections.map { it.name }
                    val receivedText = (frame as? Frame.Text ?: continue).readText()
                    val processedText = ChatService().chatContent(receivedText, thisConnection.name, allUsernames)
                    connections.forEach {
                        it.session.send(processedText)
                    }
                    /*connections.filterNot { it.equals(thisConnection) }.forEach {
                        it.session.send(processedText)
                    }*/
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                connections -= thisConnection
                println("[${thisConnection.name} leaves! There are ${connections.count()} users here.]")
            }
        }
    }
}
