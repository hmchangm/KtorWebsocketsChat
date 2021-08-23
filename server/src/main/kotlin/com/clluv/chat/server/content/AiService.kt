package com.clluv.chat.server.content

import com.beust.klaxon.Klaxon
import com.clluv.chat.server.model.AiReply
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class AiService {
    private val brainAiUrl = "http://api.brainshop.ai/get"
    private val brainAiBid = "158840"
    private val brainAiKey = "94YUpfQ2ppLRi2HY"

    private fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

    private fun aiUri(urlParams: String): URI = URI.create("${brainAiUrl}?${urlParams}")

    fun params(userName: String, message: String): Map<String, String> =
        mapOf("bid" to brainAiBid, "key" to brainAiKey, "uid" to userName, "msg" to message)

    /**
     * Connect to external AI API
     * @param message the chat content
     * @param userName the user id
     * @return the reply from AI brain
     */
    fun connectToAIForMsgReply(message: String, userName: String): String? {
        val urlParams = params(userName, message).map {(k, v) -> "${(k.utf8())}=${v.utf8()}"}.joinToString("&")
        val request = HttpRequest.newBuilder().uri(aiUri(urlParams)).build()
        val response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString())
        val reply = Klaxon().parse<AiReply.AiReplyData>(response.body())
        //println(response.body())
        return reply?.cnt
    }
}