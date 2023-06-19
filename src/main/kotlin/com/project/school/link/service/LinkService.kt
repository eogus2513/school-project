package com.project.school.link.service

import com.project.school.link.Link
import com.project.school.link.controller.dto.request.CreateLinkRequest
import com.project.school.link.controller.dto.request.UpdateLinkRequest
import com.project.school.link.controller.dto.response.GetLinksResponse
import com.project.school.link.controller.dto.response.GetLinksResponse.LinkResponse
import com.project.school.link.repository.LinkRepository
import com.project.school.utils.currentUserIdOrUnauthorized
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Component
class LinkService(
    private val linkRepository: LinkRepository,
) {
    suspend fun getAllLinks(): GetLinksResponse {
        val links = linkRepository.findAllByExpiredAtAfter(LocalDateTime.now())

        val linkResponses = links.map { link ->
            LinkResponse(
                id = link.id,
                title = link.title,
                link = link.link,
                createdAt = link.createdAt,
            )
        }
        return GetLinksResponse(linkResponses)
    }

    suspend fun createLink(request: CreateLinkRequest) {
        val currentUserId = currentUserIdOrUnauthorized()

        val link = Link(
            link = request.link,
            title = request.title,
            userId = currentUserId,
        )
        linkRepository.save(link)
    }

    suspend fun updateLink(id: Long, request: UpdateLinkRequest) {
        val link = linkRepository.findById(id) ?: throw ResponseStatusException(NOT_FOUND, "Link Not Found")
        val updateLink = link.copy(title = request.title, link = request.link)
        linkRepository.save(updateLink)
    }
}