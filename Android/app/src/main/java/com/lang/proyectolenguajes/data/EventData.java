package com.lang.proyectolenguajes.data;

import android.util.Log;

import com.lang.proyectolenguajes.viewmodel.Artist;
import com.lang.proyectolenguajes.viewmodel.Event;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class EventData {
    private static EventData eventData;

    private ArrayList<Event> events;

    private String ip = "163.178.107.10";
    private String port = "1433";
    private String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private String database = "IF4101_Proyecto_B87581_B85042";
    private String username = "laboratorios";
    private String password = "Uy&)&nfC7QqQau.%278UQ24/=%";
    private String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database + "";

    private EventData() {

    }


    public static EventData getInstance() {
        if (eventData == null) {
            eventData = new EventData();
        }
        return eventData;
    }

    public ArrayList<Event> getEvents() {
        events = new ArrayList<>();
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "EXEC sp_getEvents";
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet != null && resultSet.next()) {
                Artist a = new Artist(resultSet.getInt("ID_ARTIST"), resultSet.getString("NAME_ARTIST"), resultSet.getString("FAMOUS_SONG"), resultSet.getString("PHOTO_ARTIST"));
                //int eventId, String eventName, String campusName, String startingDate, String closingDate, int places, Artist artist

                Event e = new Event(
                        resultSet.getInt("ID_EVENT"),
                        resultSet.getString("NAME_EVENT"),
                        resultSet.getString("NAME_RECINTO"),
                        resultSet.getString("TIME_START_EVENT"),
                        resultSet.getString("TIME_END_EVENT"),
                        resultSet.getInt("TAQUILLA"),
                        resultSet.getInt("AVAIBLE"),
                        a
                );

                events.add(e);

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            Log.wtf("", e.getMessage());
        }

        return events;
    }

    public Event getEvent(int i) {
        Event result = null;
        for (Event e : events) {
            if (e.getEventId() == i) {
                result = e;
                break;
            }
        }
        return result;
    }

    public String signToEvent(String carnet, String event) {
        String result = "";
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "EXEC sp_signToEvent " + carnet + ", " + event;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null && resultSet.next()) {
                result = resultSet.getString("status");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
        }

        return result;
    }

    public ArrayList<Event> getStudentEvents(String id) {

        events = new ArrayList<>();
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "EXEC sp_getStudentEvents " + id;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet != null && resultSet.next()) {
                Artist a = new Artist(resultSet.getInt("ID_ARTIST"), resultSet.getString("NAME_ARTIST"), resultSet.getString("FAMOUS_SONG"), resultSet.getString("PHOTO_ARTIST"));

                Event e = new Event(
                        resultSet.getInt("ID_EVENT"),
                        resultSet.getString("NAME_EVENT"),
                        resultSet.getString("NAME_RECINTO"),
                        resultSet.getString("TIME_START_EVENT"),
                        resultSet.getString("TIME_END_EVENT"),
                        resultSet.getInt("TAQUILLA"),
                        resultSet.getInt("AVAIBLE"),
                        a
                );

                events.add(e);

            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            Log.wtf("", e.getMessage());
        }

        return events;
    }

    public void signOffEvent(String id, int eventId) {
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "EXEC sp_signOffEvent " + id + ", " + eventId;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
        } catch (SQLException | ClassNotFoundException e) {
            Log.wtf("", e.getMessage());
        }

    }
}
