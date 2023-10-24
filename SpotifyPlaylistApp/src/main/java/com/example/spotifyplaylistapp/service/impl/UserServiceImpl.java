package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.UserLoginDto;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class UserServiceImpl implements UserService {



    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }


    @Override
    public String register(UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterDto.getPassword()
            .equals(userRegisterDto.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return "redirect:register";
        }

        User user = checkIfExistingUser(userRegisterDto);

        if (user == null) {
            User userToSave = modelMapper.map(userRegisterDto, User.class);
            userRepository.save(userToSave);
            return "redirect:login";
        }

        return "redirect:register";
    }

    @Override
    public String loginConfirm(UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto", bindingResult);

            return "redirect:login";
        }

        User existingUser = checkIfExistingUserByUsernameAndPassword(userLoginDto);

        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("userLoginDto", userLoginDto);
            redirectAttributes.addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        currentUser.setId(existingUser.getId());
        currentUser.setUsername(existingUser.getUsername());

        return "redirect:/";
    }

    @Override
    public String isFound(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @Override
    public void addSongToUser(Long userId, Song song) {

        User user = findById(userId);

        if (user != null && !user.getPlaylist().contains(song)) {
            user.getPlaylist()
                .add(song);
            userRepository.save(user);
        }

    }


//    @Override
//    public void removeSongFromUser(Long userId, Long songId) {
//        User user = findById(userId);
//
//        if (user != null) {
//            user.getPlaylist()
//                .removeIf(s -> s.getId()
//                    .equals(songId));
//
//             userRepository.save(user);
//        }
//    }

    @Override
    public void deleteAllSongs(Long userId) {
        User user = findById(userId);
        if (user != null) {

            user.getPlaylist()
                .clear();
           userRepository.save(user);
        }
    }

    private User findById(Long id) {
        return userRepository.findById(id)
            .orElse(null);
    }

    private User checkIfExistingUser(UserRegisterDto userRegisterDto) {
        return userRepository.findUserByEmail(userRegisterDto.getEmail())
            .orElse(null);
    }

    private User checkIfExistingUserByUsernameAndPassword(UserLoginDto userLoginDto) {

        return userRepository.findUserByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword())
            .orElse(null);
    }
}
