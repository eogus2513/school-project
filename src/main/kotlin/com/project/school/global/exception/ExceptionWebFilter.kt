package com.project.school.global.exception

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class ExceptionWebFilter(
    private val objectMapper: ObjectMapper,
) : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return chain.filter(exchange)
            .onErrorResume { e ->
                mono {
                    val errorResponse = if (e is ResponseStatusException) {
                        ErrorResponse(code = e.statusCode.value(), message = "${e.reason}")
                    } else {
                        e.printStackTrace()
                        ErrorResponse(code = 500, message = e.message ?: "Internal Server Error")
                    }

                    with(exchange.response) {
                        this.statusCode = HttpStatusCode.valueOf(errorResponse.code)
                        headers.contentType = MediaType.APPLICATION_JSON
                        headers.acceptCharset = listOf(Charsets.UTF_8)
                        val dataBuffer = bufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse))
                        writeWith(dataBuffer.toMono()).awaitSingle()
                    }
                }
            }
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String,
)