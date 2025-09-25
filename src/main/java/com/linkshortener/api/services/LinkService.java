package com.linkshortener.api.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.linkshortener.api.domain.Link;
import com.linkshortener.api.repositories.LinkRepository;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    // Constructor Injection for the repository
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
    
    public String createShortLink(String longUrl) {
        // Generate a random 7-character alphanumeric string
        String shortCode = RandomStringUtils.randomAlphanumeric(7);

        // Create a new Link entity
        Link newLink = new Link(longUrl, shortCode);

        // Save the new entity to the database
        linkRepository.save(newLink);

        return shortCode;
    }
}
