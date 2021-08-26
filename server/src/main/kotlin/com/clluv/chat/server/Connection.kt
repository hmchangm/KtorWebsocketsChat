package com.clluv.chat.server

import io.ktor.http.cio.websocket.*
import java.util.concurrent.atomic.*

/**
 * Manage the WebSocket connection
 * @param session (DefaultWebSocketSession) contains everything required for communicating vai WebSockets,
 *                including the incoming and outgoing channels, convenience methods for communication.
 */
class Connection(val session: DefaultWebSocketSession) {

    companion object {
        fun <T> getName(f: (T) -> String, k: T): String = f(k)
        val genNameFromLastId: (AtomicInteger) -> String = { lastId -> "user${lastId.getAndIncrement()}" }
        val lastId = AtomicInteger(0)
    }

    val name = getName(genNameFromLastId, lastId)
    val name2 = "user${lastId.getAndIncrement()}"
}
typealias NameFunc<T> = (T) -> String