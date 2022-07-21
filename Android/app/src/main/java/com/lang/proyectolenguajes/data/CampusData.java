package com.lang.proyectolenguajes.data;

import android.util.Log;

import com.lang.proyectolenguajes.R;
import com.lang.proyectolenguajes.viewmodel.Artist;
import com.lang.proyectolenguajes.viewmodel.Campus;
import com.lang.proyectolenguajes.viewmodel.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CampusData {

    private  static CampusData campusData;
    private  String ip = "163.178.107.10";
    private  String port = "1433";
    private  String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private  String database = "IF4101_Proyecto_B87581_B85042";
    private  String username = "laboratorios";
    private  String password = "Uy&)&nfC7QqQau.%278UQ24/=%";
    private  String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database+"";
    private  CampusData() {

    }

    public static CampusData getInstance() {
        if (campusData == null) {
            campusData = new CampusData();
        }
        return campusData;
    }

    public ArrayList<Campus> getCampusList() {
        ArrayList<Campus> campuses = new ArrayList<>();
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "select * from tb_recintos";
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet != null && resultSet.next()) {
                campuses.add(
                        new Campus(resultSet.getString("NAME_RECINTO"), resultSet.getInt("ID_RECINTO"))
                );
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            Log.wtf("", e.getMessage());
        }

        Log.wtf("", campuses.size() + "");
        return campuses;
    }

    public Campus getCampus(int campusId) {
        Campus campus = null;
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "EXEC sp_getCampus " + campusId;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null && resultSet.next()) {
                campus = new Campus(resultSet.getString("NAME_RECINTO"), resultSet.getInt("ID_RECINTO"));
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            Log.wtf("", e.getMessage());
        }

        return campus;
    }
}
