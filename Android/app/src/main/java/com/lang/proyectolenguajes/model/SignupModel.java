package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.SignupData;

public class SignupModel {

    private SignupData signupData;

    public SignupModel() {
        signupData = SignupData.getInstance();
    }

    public String createUser(String userName, String password) {
        String result = "error";
        result = signupData.createUser(userName, password);
        return result;
    }
}
