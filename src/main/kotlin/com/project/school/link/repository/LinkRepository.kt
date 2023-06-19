package com.project.school.link.repository

import com.project.school.link.Link
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface LinkRepository : CoroutineCrudRepository<Link, Long> {
    suspend fun findAllByExpiredAtAfter(expiredAt: LocalDateTime): List<Link>
}