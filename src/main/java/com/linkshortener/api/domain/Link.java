package com.linkshortener.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Link {
    @Id
    @GeneratedValue
    private Long id;

    private String longUrl;

    private String shortCode;

    // Constructors, Getters and Setters are needed by JPA.
    // We'll add them next.

    public Link() {
    }

    public Link(String longUrl, String shortCode) {
        this.longUrl = longUrl;
        this.shortCode = shortCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
