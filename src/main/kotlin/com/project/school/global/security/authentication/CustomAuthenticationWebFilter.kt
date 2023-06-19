package com.project.school.global.security.authentication

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

@Configuration
class CustomAuthenticationWebFilter {
    @Bean
    fun authenticationWebFilter(
        customAuthenticationManager: CustomAuthenticationManager,
        customAuthenticationConverter: CustomAuthenticationConverter,
    ): AuthenticationWebFilter {
        return AuthenticationWebFilter(customAuthenticationManager).apply {
            setServerAuthenticationConverter(customAuthenticationConverter)
        }
    }
}