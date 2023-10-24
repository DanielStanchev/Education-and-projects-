package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.SongAddDto;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongAddController {

    private final SongService songService;

    public SongAddController(SongService songService) {
        this.songService = songService;

    }

    @GetMapping("/add")
    public String add() {
        return "song-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid SongAddDto songAddDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        return songService.add(songAddDto, bindingResult, redirectAttributes);
    }


    @ModelAttribute
    public SongAddDto songAddDto() {
        return new SongAddDto();
    }
}
