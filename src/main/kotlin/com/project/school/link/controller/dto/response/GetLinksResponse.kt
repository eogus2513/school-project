package com.project.school.link.controller.dto.response

import java.time.LocalDateTime

data class GetLinksResponse(
    val links: List<LinkResponse>,
) {
    data class LinkResponse(
        val id: Long,
        val title: String,
        val link: String,
        val createdAt: LocalDateTime,
    )
}