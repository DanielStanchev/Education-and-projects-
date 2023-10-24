package com.example.coffeeshopweb.service.impl;

import com.example.coffeeshopweb.model.dto.UserLoginDto;
import com.example.coffeeshopweb.model.dto.UserRegisterDto;
import com.example.coffeeshopweb.model.dto.UserViewDto;
import com.example.coffeeshopweb.model.entity.User;
import com.example.coffeeshopweb.repository.UserRepository;
import com.example.coffeeshopweb.service.UserService;
import com.example.coffeeshopweb.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;


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
    public String login(UserLoginDto userLoginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewDto> findAllUserAndCountOfOrdersDesc() {

        return userRepository.findAllByOrdersDesc().stream()
            .map(user -> {
                UserViewDto userViewDto = new UserViewDto();
                userViewDto.setUsername(user.getUsername());
                userViewDto.setCountOfOrders(user.getOrders().size());

                return userViewDto;

            }).collect(Collectors.toList());
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
