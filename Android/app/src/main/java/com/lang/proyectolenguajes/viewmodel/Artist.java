package com.lang.proyectolenguajes.viewmodel;

public class Artist {

    private int id;
    private String name;
    private String song;

    public Artist(int id, String name, String song) {
        this.id = id;
        this.name = name;
        this.song = song;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSong() {
        return song;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
