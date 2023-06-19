package com.project.school.link.controller

import com.project.school.link.controller.dto.request.CreateLinkRequest
import com.project.school.link.controller.dto.request.UpdateLinkRequest
import com.project.school.link.controller.dto.response.GetLinksResponse
import com.project.school.link.service.LinkService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/link")
class LinkController(
    private val linkService: LinkService,
) {
    @ResponseStatus(OK)
    @GetMapping("/all")
    suspend fun getAllLinks(): GetLinksResponse {
        return linkService.getAllLinks()
    }

    @ResponseStatus(CREATED)
    @PostMapping
    suspend fun createLink(@RequestBody @Valid request: CreateLinkRequest) {
        linkService.createLink(request)
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping("/{id}")
    suspend fun updateLink(@PathVariable id: Long, @RequestBody @Valid request: UpdateLinkRequest) {
        linkService.updateLink(id, request)
    }
}