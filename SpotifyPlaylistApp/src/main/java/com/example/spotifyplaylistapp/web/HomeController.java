package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.SongDto;
import com.example.spotifyplaylistapp.model.dto.SongsByGenreDto;
import com.example.spotifyplaylistapp.service.HomeService;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final HomeService homeService;
    private final SongService songService;

    public HomeController(CurrentUser currentUser, HomeService homeService, SongService songService) {
        this.currentUser = currentUser;
        this.homeService = homeService;
        this.songService = songService;
    }

    @GetMapping
    public String index(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("songs", homeService.getSongs());
        model.addAttribute("playlist", songService.getPlaylist(currentUser.getId()));
        model.addAttribute("totalTime", songService.getPlaylist(currentUser.getId())
            .stream()
            .map(SongDto::getDuration)
            .reduce(Integer::sum)
            .orElse(0));


        return "home";
    }

    @GetMapping("/home/add-song-to-playlist/{id}")
    public String addSongToPlaylist(@PathVariable Long id) {

        homeService.addSong(id, currentUser.getId());
        return "redirect:/";
    }

    //    @GetMapping("/home/remove-song-from-playlist/{id}")
    //    public String removeSongFromPlaylist(@PathVariable Long id) {
    //
    //        homeService.removeSong(id, currentUser.getId());
    //        return "redirect:/";
    //    }

    @GetMapping("/home/remove-all-song-from-playlist")
    public String deleteAllFromPlaylist() {

        homeService.deleteAll(currentUser.getId());
        return "redirect:/";
    }

    @ModelAttribute
    public SongsByGenreDto songs() {
        return new SongsByGenreDto();
    }
}


