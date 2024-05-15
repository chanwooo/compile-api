package com.example.compileapi

import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CompileService(
    val compileRepository: CompileRepository
) {

    companion object {
        val logger = KotlinLogging.logger {}
    }

    fun compile(code: String): String {
        return excutePython(code)
    }

    fun excutePython(code: String): String {
        val processBuilder = ProcessBuilder()
        processBuilder.command("python3", "-c", code)
        logger.info { "process: ${processBuilder.command()}" }
        val process = processBuilder.start()
        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()
        return error.ifEmpty {
            output
        }

    }

    fun saveCode(code: Code): Code {
        return compileRepository.save(code)
    }

    fun getCodes(): List<Code> {
        return compileRepository.findAll()
    }

    fun getCode(id: Long): Code {
        return compileRepository.findById(id).orElseThrow()
    }

}
