package com.clluv.chat.server.service

class ChatService {
    /**
     * To process the chat content received from the client
     * @param receivedText the text sent from the client
     * @param userName this client's connection name
     * @return the processed content to send to others
     */
    fun chatContent(receivedText: String, userName: String, allUsernames: List<String>): String {
        val returnText = when (receivedText) {
            "[[[adding]]]" -> "[${userName} joins.]"
            "[[[leaving]]]" -> "[${userName} leaves the chatroom.]"
            "/commands" -> "listUser, exit"
            "listUser" ->  allUsernames.toString()
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
    private fun aiReply(receivedText: String, userName: String): String {
        var aiRepliedMsg = ""
        try {
            aiRepliedMsg = AiService().connectToAIForMsgReply(receivedText, userName) ?: ""
            if (aiRepliedMsg.isNotEmpty()) {
                aiRepliedMsg = "[LunLunBot]: ".plus(aiRepliedMsg)
            }
            aiRepliedMsg = aiRepliedMsg.replace(" Aco", " LunLunBot")
        } catch (e: Exception) {
            println(e.localizedMessage)
        }
        return aiRepliedMsg.ifEmpty { "***LunLunBot is not connected!" }
    }
}