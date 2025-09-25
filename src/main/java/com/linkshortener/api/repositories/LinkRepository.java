package com.linkshortener.api.repositories;

import com.linkshortener.api.domain.Link;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findByShortCode(String shortCode);
}
