package com.project.school.link

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table(name = "tbl_link")
data class Link(
    @Id
    val id: Long = 0,
    val link: String,
    val title: String,
    val userId: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val expiredAt: LocalDateTime = LocalDateTime.now().plusWeeks(1)
)