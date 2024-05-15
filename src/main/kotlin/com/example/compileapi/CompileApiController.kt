package com.example.compileapi

import mu.KotlinLogging
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/code")
class CompileApiController(
    val compileService: CompileService
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @GetMapping("/{code}")
    fun simpleCompile(@PathVariable code: String): String {
        return compileService.compile(code)
    }

    @PostMapping("/compile")
    fun compile(@RequestBody request: CompileCodeRequest): CompileCodeResponse {
        logger.info { "request: ${request.code}" }
        return compileService.compile(request.code).let {
            CompileCodeResponse(it)
        }
    }

    @PostMapping
    fun postCode(postCodeRequest: PostCodeRequest): Code {
        return compileService.saveCode(postCodeRequest.toCode())
    }

    @GetMapping
    fun getCodes(): List<Code> {
        return compileService.getCodes()
    }

    class CompileCodeRequest(
        val code: String
    )

    class CompileCodeResponse(
        val result: String
    )


    class PostCodeRequest(
        val title: String,
        val code: String,
        val author: String,
        val language: String,
    ){
        fun toCode(): Code {
            return Code(title, code, author, language)
        }
    }


}