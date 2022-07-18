package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.LoginData;

public class LoginModel {

    private LoginData loginData;
    public LoginModel() {
        loginData = LoginData.getInstance();
    }

    public String login(String username, String password) {
        return loginData.login(username, password);
    }
}
