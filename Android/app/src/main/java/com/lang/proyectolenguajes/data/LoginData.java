package com.lang.proyectolenguajes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginData {

    private static LoginData loginData;

    private  String ip = "163.178.107.10";
    private  String port = "1433";
    private  String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private  String database = "IF4101_Proyecto_B87581_B85042";
    private  String username = "laboratorios";
    private  String password = "Uy&)&nfC7QqQau.%278UQ24/=%";
    private  String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private LoginData() {

    }


    public static LoginData getInstance() {
        if (loginData == null) {
            loginData = new LoginData();
        }
        return loginData;
    }

    public String login(String username, String password) {
        String result = "";
        try {
            Class.forName(Classes);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            Statement statement = connection.createStatement();
            String query = "exec sp_studentLogin " + username + ", " + password;
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                result =resultSet.getString("status");
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            result += e.getMessage();
        }

        return result;
    }

}
