package com.linkshortener.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkshortener.api.dto.CreateLinkRequest;
import com.linkshortener.api.services.LinkService;

@RestController
@RequestMapping("/links")
public class LinkController {

    private final LinkService linkService;

    // Constructor Injection: Spring automatically provides the LinkService bean.
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public String createShortLink(@RequestBody CreateLinkRequest request) {
        // Delegate the business logic to the service layer
        return linkService.createShortLink(request.longUrl());
    }
}
