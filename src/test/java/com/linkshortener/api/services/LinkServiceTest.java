package com.linkshortener.api.services;

import com.linkshortener.api.repositories.LinkRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;

import com.linkshortener.api.repositories.LinkRepository;
import com.linkshortener.api.domain.Link;

@ExtendWith(MockitoExtension.class)
class LinkServiceTest {
    
    @Mock
    private LinkRepository  linkRepository;

    @InjectMocks
    private LinkService linkService;

    @Test
    void createShortLink_shouldReturn7CharacteString() {
        // Given (Arrange)
        String longUrl = "https://www.example.com";

        // When (Act)
        String shortCode = linkService.createShortLink(longUrl);

        // Then (Assert)
        assertNotNull(shortCode);
        assertEquals(7, shortCode.length());
    }

    @Test
    void createShortLink_shouldSaveLinkToRepository() {
        //Given
        String longUrl = "https://www.example.com";

        // When
        linkService.createShortLink(longUrl);

        // Then
        verify(linkRepository).save(any(Link.class));
        
    }
}
