package com.linkshortener.api.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class LinkService {
    
    public String createShortLink(String longUrl) {
        // Generate a random 7-character alphanumeric string
        String shortCode = RandomStringUtils.randomAlphanumeric(7);

        // We'll save this to the database later.
        // For now, we'll just return it.
        return shortCode;
    }
}
