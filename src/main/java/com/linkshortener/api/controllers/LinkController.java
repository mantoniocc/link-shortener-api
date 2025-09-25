package com.linkshortener.api.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.linkshortener.api.dto.CreateLinkRequest;
import com.linkshortener.api.services.LinkService;
import com.linkshortener.api.exceptions.LinkNotFoundException;


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

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String longUrl = linkService.getLongUrlByShortCode(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", longUrl);

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<String> handleLinkNotFound(LinkNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
}
