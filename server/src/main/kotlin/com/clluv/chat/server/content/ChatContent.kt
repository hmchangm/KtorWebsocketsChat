package com.clluv.chat.server.content

fun chatContent(receivedText: String, userName: String): String {
    val returnText = when (receivedText) {
        "[[[adding]]]" -> "[${userName} joins.]"
        "[[[leaving]]]" -> "[${userName} leaves the chatroom.]"
        else -> "[${userName}]: ${receivedText}\n${askAIBrain(receivedText, userName)}"
    }
    return returnText
}

fun askAIBrain(receivedText: String, userName: String): String {
    return (replyContentFromAI(receivedText, userName) ?: "").replace(" Aco", " LunLunBot")
}
