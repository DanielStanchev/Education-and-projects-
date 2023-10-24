package com.example.coffeeshopweb.web;


import com.example.coffeeshopweb.model.dto.UserLoginDto;
import com.example.coffeeshopweb.model.dto.UserRegisterDto;
import com.example.coffeeshopweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        return userService.register(userRegisterDto, bindingResult, redirectAttributes);
    }

    @GetMapping("/login")
    public String login(Model model) {

        return userService.isFound(model);
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        return userService.login(userLoginDto, bindingResult, redirectAttributes);
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession){

        httpSession.invalidate();

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
