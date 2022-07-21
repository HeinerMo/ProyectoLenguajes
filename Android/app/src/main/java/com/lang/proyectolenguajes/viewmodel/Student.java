package com.lang.proyectolenguajes.viewmodel;

public class Student {
    private String name;
    private String id;
    private String password;
    private Campus campus;

    public Student(String name, String id, String password, Campus campus) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.campus = campus;
    }

    public String toString() {
        return "Student [Name: " + name + " ID: " + id + "Password: " + password + "]";
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Campus getCampus() {
        return campus;
    }
}
