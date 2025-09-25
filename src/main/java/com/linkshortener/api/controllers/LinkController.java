package com.linkshortener.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
public class LinkController {

    @PostMapping
    public String createShortLink(@RequestBody String longUrl) {
        // For now, we'll just return the URL we received to confirm it works
        return "Recieved long URL: " + longUrl;
    }
}
