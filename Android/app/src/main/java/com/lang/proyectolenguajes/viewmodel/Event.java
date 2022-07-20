package com.lang.proyectolenguajes.viewmodel;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class Event {
    private int  eventId;
    private String eventName;
    private String campusName;
    private String startingDate;
    private String closingDate;
    private int places;
    private Artist artist;

    public Event(int eventId, String eventName, String campusName, String startingDate, String closingDate, int places, Artist artist) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.campusName = campusName;
        this.startingDate = startingDate;
        this.closingDate = closingDate;
        this.places = places;
        this.artist = artist;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCampusName() {
        return campusName;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public int getPlaces() {
        return places;
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", campusName='" + campusName + '\'' +
                ", startingDate=" + startingDate +
                ", closingDate=" + closingDate +
                ", places=" + places +
                ", artist=" + artist +
                '}';
    }
}
