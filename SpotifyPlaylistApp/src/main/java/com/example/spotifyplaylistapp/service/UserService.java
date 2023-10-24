package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.UserLoginDto;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UserService {

    String register(UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    String loginConfirm(UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    String isFound(Model model);

    void addSongToUser(Long userId, Song song);

  //  void removeSongFromUser(Long userId, Long songId);

    void deleteAllSongs(Long userId);


}
