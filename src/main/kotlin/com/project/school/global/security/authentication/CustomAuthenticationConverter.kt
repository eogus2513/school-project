package com.project.school.global.security.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.reactor.mono
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class CustomAuthenticationConverter(
    @Value("\${jwt.secret}") private val secret: String,
) : ServerAuthenticationConverter {
    companion object {
        const val BEARER = "Bearer "
    }

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        return mono {
            val headerToken = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.firstOrNull()

            headerToken?.let {
                val parseToken = parseTokenOrUnauthorized(headerToken.replace(BEARER, ""))
                UsernamePasswordAuthenticationToken(parseToken.body.subject, "")
            }
        }
    }

    private fun parseTokenOrUnauthorized(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build()
                .parseClaimsJws(token)
        } catch (e: JwtException) {
            e.printStackTrace()
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Jwt Parse Error")
        }
    }
}