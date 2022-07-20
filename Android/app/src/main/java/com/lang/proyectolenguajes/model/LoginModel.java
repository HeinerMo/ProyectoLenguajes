package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.LoginData;
import com.lang.proyectolenguajes.viewmodel.Student;

public class LoginModel {

    private LoginData loginData;
    public LoginModel() {
        loginData = LoginData.getInstance();
    }

    public String login(String carnet, String password) {
        return loginData.login(carnet, password);
    }

    public void setStudent(String s) {
        loginData.setStudent(s);
    }

    public Student getStudent() {
        return loginData.getStudent();
    }
}
