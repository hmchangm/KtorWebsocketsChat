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
        // AtomicInteger: thread-safe data structure for the counter.
        // To ensure 2 users will never receive the same ID even when their Connection objects are created simultaneously on separate threads.
        var lastId = AtomicInteger(0)
    }
    val name = "user${com.clluv.chat.server.Connection.Companion.lastId.getAndIncrement()}"
}
