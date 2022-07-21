package com.lang.proyectolenguajes.model;

import com.lang.proyectolenguajes.data.EventData;
import com.lang.proyectolenguajes.viewmodel.Event;

import java.util.ArrayList;

public class EventModel {

    private EventData eventData;

    public EventModel() {
        eventData = EventData.getInstance();
    }



    public ArrayList<Event> getEvents() {
        return eventData.getEvents();
    }


    public Event getEvent(int i) {
        return eventData.getEvent(i);
    }

    public String signToEvent(String carnet, String event ) {
        return eventData.signToEvent(carnet, event);
    }

    public ArrayList<Event> getStudentEvents(String id) {
        return eventData.getStudentEvents(id);
    }

    public void signOffEvent(String id, int eventId) {
        eventData.signOffEvent(id, eventId);
    }

    public ArrayList<Event> getEvents(int campusId) {
        return eventData.getEvents(campusId);
    }

    public String addComment(String comment, int artistId, String studentId) {
        return eventData.addComment(comment, artistId, studentId);
    }
}
