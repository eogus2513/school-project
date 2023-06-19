package com.project.school.global.security.web

import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Component
class WebConfig : WebFluxConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedMethods("*")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .maxAge(3600)
    }
}