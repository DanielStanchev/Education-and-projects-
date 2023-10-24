package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.SongsByGenreDto;

public interface HomeService {

    SongsByGenreDto getSongs();

    void addSong(Long songId, Long userId);

   // void removeSong(Long songId, Long userId);

    void deleteAll(Long userId);



}
