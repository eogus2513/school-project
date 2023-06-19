package com.project.school.global.security.authentication

import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class CustomAuthenticationManager : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return mono {
            UsernamePasswordAuthenticationToken(authentication.principal, "", listOf())
        }
    }
}