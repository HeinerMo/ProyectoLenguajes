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
}
