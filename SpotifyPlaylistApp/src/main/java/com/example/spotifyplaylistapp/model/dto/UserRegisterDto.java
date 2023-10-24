package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterDto {

    private Long id;

    @Size(min = 3, max = 20)
    private String username;

    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @Size(min = 3,max = 20)
    private String password;

    @Size(min = 3,max = 20)
    private String confirmPassword;

    public UserRegisterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
