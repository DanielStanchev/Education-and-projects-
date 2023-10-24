package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.SongAddDto;
import com.example.spotifyplaylistapp.model.dto.SongDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

public interface SongService {

    String add(SongAddDto songAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    Song findSongById(Long id);

    Set<SongDto> findSongsByStyle(Style style);

    Set<SongDto> getPlaylist(Long id);
}
