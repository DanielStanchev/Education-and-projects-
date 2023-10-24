package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style,Long> {
    Optional<Style> findStyleByStyleName(StyleEnum style);
}
