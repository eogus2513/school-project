package com.project.school.utils

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.ResponseStatusException

suspend fun currentUserIdOrUnauthorized(): String =
    ReactiveSecurityContextHolder.getContext().awaitSingleOrNull()?.authentication?.name
        ?: throw ResponseStatusException(NOT_FOUND, "User Context Not Found")