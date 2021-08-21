package com.clluv.chat.server.content

fun chatContent(receivedText: String, userName: String): String {
    val returnText = when (receivedText) {
        "[[[adding]]]" -> "[${userName} joins.]"
        "[[[leaving]]]" -> "[${userName} leaves the chatroom.]"
        else -> "[${userName}]: ${receivedText}\n${askAIBrain(receivedText, userName)}"
    }
    return returnText
}

fun aiReply(receivedText: String, userName: String): String {
    var aiRepliedMsg = ""
    try {
        aiRepliedMsg = replyContentFromAI(receivedText, userName) ?: ""
    } catch (e: Exception) {
        println(e.localizedMessage)
    } finally {
        if (aiRepliedMsg.isNullOrEmpty()) {
            aiRepliedMsg = "***LunLunBot is not connected!"
        }
    }
    return aiRepliedMsg
}

fun askAIBrain(receivedText: String, userName: String): String {
    return aiReply(receivedText, userName).replace(" Aco", " LunLunBot")
    //return (replyContentFromAI(receivedText, userName) ?: "").replace(" Aco", " LunLunBot")
}

