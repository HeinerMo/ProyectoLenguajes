package com.lang.proyectolenguajes.viewmodel;

public class Artist {

    private int id;
    private String name;
    private String song;
    private String imgName;

    public Artist(int id, String name, String song, String imgName) {
        this.id = id;
        this.name = name;
        this.song = song;
        this.imgName = imgName;
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

    public String getImgName() {
        return imgName;
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
