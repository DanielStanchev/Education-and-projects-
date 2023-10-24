package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.SongAddDto;
import com.example.spotifyplaylistapp.model.dto.SongDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashSet;
import java.util.Set;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
    }


    @Override
    public String add(SongAddDto songAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songAddDto", songAddDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddDto", bindingResult);

            return "redirect:add";
        }

        Song songToSave = modelMapper.map(songAddDto, Song.class);

        songToSave.setStyle(styleService.findStyleByStyleName(songAddDto.getStyle()));

        songRepository.save(songToSave);

        return "redirect:/";
    }

    @Override
    public Song findSongById(Long id) {
        return songRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<SongDto> findSongsByStyle(Style style) {

       Set<Song> songs = songRepository.findAllByStyle(style);


        Set<SongDto> songDtos = new HashSet<>();

        for (Song song : songs) {
            SongDto songDto = modelMapper.map(song, SongDto.class);
            songDtos.add(songDto);
        }

        return songDtos;
    }

    @Override
    public Set<SongDto> getPlaylist(Long id) {

        Set<Song> songs = songRepository.findAllByUserId(id);

        Set<SongDto> songDtos = new HashSet<>();

        for (Song song : songs) {
            SongDto songDto = modelMapper.map(song, SongDto.class);
            songDtos.add(songDto);
        }

        return songDtos;
    }

}
