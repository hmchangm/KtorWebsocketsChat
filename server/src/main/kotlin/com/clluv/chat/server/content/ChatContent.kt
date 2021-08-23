package com.clluv.chat.server.content

/**
 * To process the chat content received from the client
 * @param receivedText the text sent from the client
 * @param userName this client's connection name
 * @return the processed content to send to others
 */
fun chatContent(receivedText: String, userName: String): String {
    val returnText = when (receivedText) {
        "[[[adding]]]" -> "[${userName} joins.]"
        "[[[leaving]]]" -> "[${userName} leaves the chatroom.]"
        else -> "[${userName}]: ${receivedText}\n${aiReply(receivedText, userName)}"
    }
    return returnText
}

/**
 * Get reply from AI brain
 * @param receivedText the text from client side and then pass to AI brain for answer
 * @userName userName this client's connection name
 * @return the reply from AI brain
 */
fun aiReply(receivedText: String, userName: String): String {
    var aiRepliedMsg = ""
    try {
        aiRepliedMsg = AiService().connectToAIForMsgReply(receivedText, userName) ?: ""
        aiRepliedMsg = aiRepliedMsg.replace(" Aco", " LunLunBot")
    } catch (e: Exception) {
        println(e.localizedMessage)
    }
    return aiRepliedMsg.ifEmpty { "***LunLunBot is not connected!" }
}