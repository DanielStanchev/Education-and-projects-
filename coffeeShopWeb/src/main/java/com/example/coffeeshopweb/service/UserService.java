package com.example.coffeeshopweb.service;

import com.example.coffeeshopweb.model.dto.UserLoginDto;
import com.example.coffeeshopweb.model.dto.UserRegisterDto;
import com.example.coffeeshopweb.model.dto.UserViewDto;
import com.example.coffeeshopweb.model.entity.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

public interface UserService {

    String register(UserRegisterDto userRegisterDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    String login(UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes);

    String isFound(Model model);

    User findById(Long id);

    List<UserViewDto> findAllUserAndCountOfOrdersDesc();
}
