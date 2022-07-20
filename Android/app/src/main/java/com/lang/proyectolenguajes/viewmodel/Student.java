package com.lang.proyectolenguajes.viewmodel;

public class Student {
    private String name;
    private int id;
    private String password;

    public Student(String name, int id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String toString() {
        return "Student [Name: " + name + " ID: " + id + "Password: " + password + "]";
    }
}
