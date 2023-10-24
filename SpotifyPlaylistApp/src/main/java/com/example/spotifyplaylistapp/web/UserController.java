package com.example.spotifyplaylistapp.web;

import com.example.spotifyplaylistapp.model.dto.UserLoginDto;
import com.example.spotifyplaylistapp.model.dto.UserRegisterDto;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public final CurrentUser currentUser;


    public UserController(UserService userService, CurrentUser currentUser) {this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return userService.register(userRegisterDto,bindingResult,redirectAttributes);

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        return userService.loginConfirm(userLoginDto,bindingResult,redirectAttributes);

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){

        httpSession.invalidate();
        currentUser.setId(null);
        currentUser.setUsername(null);

        return "redirect:/";
    }


    @ModelAttribute
    public UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @ModelAttribute
    public UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }


}
