package com.linkshortener.api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasLength;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.linkshortener.api.repositories.LinkRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

   @Autowired 
   private LinkRepository linkRepository;
   
   @BeforeEach
    void setUp() {
        linkRepository.deleteAll();
    }

    @Test
    void createShortLink_whenValidRequest_shouldSucceed() throws Exception {
        // Given
        String requestJson = "{\"longUrl\": \"https://example.com/my-long-url\"}";

        // When & Then
        mockMvc.perform(post("/links")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string(hasLength(7)));

        // Verify database state
        assertEquals(1, linkRepository.count());
    }
}
