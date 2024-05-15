package com.example.compileapi

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CompileServiceTest {

    @Autowired
    lateinit var compileService: CompileService

    @Test
    fun compile() {
        val result = compileService.compile(
"""
print("Hello, World!")
print('Hello, World!')
""".trimMargin())
        println(result)
        assertEquals("Hello, World!\nHello, World!\n", result)
    }


    @Test
    fun excuteCmd() {
        val result = compileService.excutePython("print(\'Hello, World!\'   )")
        println(result)
        assertEquals("Hello, World!\n", result)
    }


}