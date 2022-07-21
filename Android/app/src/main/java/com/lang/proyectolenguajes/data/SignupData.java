package com.lang.proyectolenguajes.data;


import android.os.StrictMode;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.Result;

public class SignupData {

    private static SignupData instance;

    private  String ip = "163.178.107.10";
    private  String port = "1433";
    private  String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private  String database = "IF4101_Proyecto_B87581_B85042";
    private  String username = "laboratorios";
    private  String password = "Uy&)&nfC7QqQau.%278UQ24/=%";
    private  String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private  SignupData() {

    }


    public static SignupData getInstance() {
        if (instance == null) {
            instance = new SignupData();
        }

        return instance;
    }

    public String createUser(String carnet, String userName, String password, int campus) {
        String result = "";
        try {
            Class.forName(this.Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "exec sp_addStudent " + userName + ", " + password + ", " + carnet + ", " + campus;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null && resultSet.next()) {
                if (resultSet.getString("status").equalsIgnoreCase("error")) {
                    result = resultSet.getString("error");
                } else if (resultSet.getString("status").equalsIgnoreCase("success")) {
                    result = "success";
                }
            } else {
                result = "success";
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            result = e.getMessage();
        }
        return result;
    }

}
