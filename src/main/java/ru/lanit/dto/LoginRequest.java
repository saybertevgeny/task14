package ru.lanit.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    public String getLogin() {
        return login;
    }

    public LoginRequest setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
