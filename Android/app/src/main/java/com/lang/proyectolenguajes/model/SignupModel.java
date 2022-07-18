package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.SignupData;

public class SignupModel {

    private SignupData signupData;

    public SignupModel() {
        signupData = SignupData.getInstance();
    }

    public String createUser(String userName, String password) {
        String result = "error";
        //Check if username is available
        String temp = signupData.existsUsername(userName);
        if (temp.equalsIgnoreCase("true")) {
            result = "username";
        }if (temp.equalsIgnoreCase("error")) {
            result = "error";
        }if (temp.equalsIgnoreCase("false")) {
            result = signupData.createUser(userName, password);
        }

        return result;
    }
}
