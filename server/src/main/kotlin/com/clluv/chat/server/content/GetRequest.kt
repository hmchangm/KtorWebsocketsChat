package com.clluv.chat.server.content

import com.beust.klaxon.Klaxon
import com.clluv.chat.server.model.AiReply
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

const val brainAiUrl = "http://api.brainshop.ai/get"
const val bid = "158840"
const val key = "94YUpfQ2ppLRi2HY"

fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

fun connectToAIForMsgReply(message: String, userName: String): String? {
    val params = mapOf("bid" to bid, "key" to key, "uid" to userName, "msg" to message)
    val urlParams = params.map {(k, v) -> "${(k.utf8())}=${v.utf8()}"}.joinToString("&")
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("${brainAiUrl}?${urlParams}"))
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val reply = Klaxon().parse<AiReply.AiReplyData>(response.body())
    //println(response.body())
    return reply?.cnt
}