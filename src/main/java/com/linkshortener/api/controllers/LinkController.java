package com.linkshortener.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linkshortener.api.dto.CreateLinkRequest;

@RestController
@RequestMapping("/links")
public class LinkController {

    @PostMapping
    public String createShortLink(@RequestBody CreateLinkRequest request) {
        // Spring now automatically parses the JSON into our DTO.
        // We can access the longUrl directly.
        return "Recieved long URL: " + request.longUrl();
    }
}
