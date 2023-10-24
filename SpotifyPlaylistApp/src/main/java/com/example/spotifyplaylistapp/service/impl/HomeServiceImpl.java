package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.SongDto;
import com.example.spotifyplaylistapp.model.dto.SongsByGenreDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import com.example.spotifyplaylistapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HomeServiceImpl implements HomeService {

    private final SongService songService;
    private final StyleService styleService;
    private final UserService userService;

    public HomeServiceImpl(SongService songService, StyleService styleService, UserService userService) {
        this.songService = songService;
        this.styleService = styleService;
        this.userService = userService;
    }

    @Override
    public SongsByGenreDto getSongs() {
        SongsByGenreDto songs = new SongsByGenreDto();
        songs.setPopSongs(getSongsByGenre(styleService.findStyleByStyleName(StyleEnum.POP)));
        songs.setJazzSongs(getSongsByGenre(styleService.findStyleByStyleName(StyleEnum.JAZZ)));
        songs.setRockSongs(getSongsByGenre(styleService.findStyleByStyleName(StyleEnum.ROCK)));
        return songs;
    }

    @Override
    public void addSong(Long songId, Long userId) {
        Song song = songService.findSongById(songId);
        userService.addSongToUser(userId, song);
    }

//    @Override
//    public void removeSong(Long songId, Long userId) {
//        userService.removeSongFromUser(userId, songId);
//    }

    @Override
    public void deleteAll(Long userId) {
        userService.deleteAllSongs(userId);
    }

    private Set<SongDto> getSongsByGenre(Style style) {
        return songService.findSongsByStyle(style);
    }
}
