package com.example.spotifyplaylistapp.model.dto;

import java.util.Set;

public class PlaylistDto {

    private Set<SongDto> playlist;

    public PlaylistDto() {
    }

    public Set<SongDto> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Set<SongDto> playlist) {
        this.playlist = playlist;
    }
}
