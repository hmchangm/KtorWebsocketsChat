package com.clluv.chat.server.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AiServiceTest {

    private val aiService: AiService = AiService()

    @Test
    fun params() {
        val expected = mapOf("bid" to "158840", "key" to "94YUpfQ2ppLRi2HY", "uid" to "user0", "msg" to "Hello")
        assertEquals(expected, aiService.params("user0", "Hello"))
    }
}