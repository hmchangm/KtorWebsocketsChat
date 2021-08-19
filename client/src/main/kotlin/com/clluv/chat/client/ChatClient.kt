package com.clluv.chat.client

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.util.*
import kotlinx.coroutines.*

fun main() {
    val client = HttpClient {
        install(WebSockets)
    }
    /*
     * Functions in Ktor responsible for making network calls use the suspension mechanism from Kotlin's coroutines,
     * so we wrap our network-related code in a runBlocking block
     */
    runBlocking {
        client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
            /*
             * Separate the message output and input mechanisms,
             * allowing them to run concurrently.
             * When new messages arrive, they are printed immediately, but our users can still start composing a new chat message at any point.
             */
            val messageOutputRoutine = launch { outputMessages() }
            val userInputRoutine = launch { inputMessages() }

            userInputRoutine.join() // Wait for completion; either "exit" or error
            messageOutputRoutine.cancelAndJoin()
        }
    }
    client.close()
    println("Connection closed. Goodbye!")
}

/*
 * Suspending functions are the center of everything coroutines
 * A suspending function is simply a function that can be paused and resumed at a later time.
 * They can execute a long running operation and wait for it to complete without blocking.
 */
/*
 * Because the function operates in the context of a DefaultClientWebSocketSession,
 * define it as an extension function on the type.
 */
suspend fun DefaultClientWebSocketSession.outputMessages() {
    try {
        for (message in incoming) {
            message as? Frame.Text ?: continue
            println(message.readText())
        }
    } catch (e: Exception) {
        println("Error while receiving: " + e.localizedMessage)
    }
}

/*
 * To read text from the command line and send it to the server,
 * or to return when the user types exit.
 */
suspend fun DefaultClientWebSocketSession.inputMessages() {
    while (true) {
        val message = readLine() ?: ""
        if (message.equals("exit", true)) return
        try {
            send(message)
        } catch (e: Exception) {
            println("Error while sending: " + e.localizedMessage)
            return
        }
    }
}