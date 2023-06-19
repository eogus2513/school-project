package com.project.school.link.controller.dto.request

import jakarta.validation.constraints.NotBlank

data class CreateLinkRequest(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val link: String,
)