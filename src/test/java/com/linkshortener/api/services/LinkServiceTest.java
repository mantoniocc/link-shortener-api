package com.linkshortener.api.services;

import com.linkshortener.api.repositories.LinkRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import com.linkshortener.api.repositories.LinkRepository;
import com.linkshortener.api.domain.Link;
import com.linkshortener.api.exceptions.LinkNotFoundException;

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

    @Test
    void getLongUrlByShortCode_whenCodeExists_shouldReturnLongUrl() {
        // Given
        String shortCode = "abc1234";
        String longUrl = "https://www.example.com";
        Link mockLink = new Link(longUrl, shortCode);

        // Give the mock its script:
        // "When findByShortCode is called with "abc1234", then return our mockLink"
        when(linkRepository.findByShortCode(shortCode)).thenReturn(Optional.of(mockLink));

        // When
        String result = linkService.getLongUrlByShortCode(shortCode);

        // Then
        assertEquals(longUrl, result);
    }

    @Test
    void getLongUrlByShortCode_whenCodeDoesNotExist_shouldThrowException() {
        // Given
        String nonExistentCode = "xxxxxxx";

        // Give the mock its script:
        // "When findByShortCode is called, then return an empty Optional"
        when(linkRepository.findByShortCode(nonExistentCode)).thenReturn(Optional.empty());

        // When & Then
        // Verify that calling the method throws the expected exception
        assertThrows(LinkNotFoundException.class, () -> {
            linkService.getLongUrlByShortCode(nonExistentCode);
        });
    }
}