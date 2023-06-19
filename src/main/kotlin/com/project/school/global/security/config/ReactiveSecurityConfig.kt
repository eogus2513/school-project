package com.project.school.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter

@Configuration
@EnableWebFluxSecurity
class ReactiveSecurityConfig(
    private val authenticationWebFilter: AuthenticationWebFilter,
) {
    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            csrf { disable() }
            cors { disable() }
            formLogin { disable() }

            addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)

            authorizeExchange {
                authorize("/link/**", authenticated)
            }
        }
    }
}