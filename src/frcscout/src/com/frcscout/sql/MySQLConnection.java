package com.frcscout.sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection implements DBConnection {
    private Connection conn;
    private String url;
    private String dbName;
    private String driver;
    private String userName; 
    private String password;
    public MySQLConnection() {
        conn = null;
        url = "jdbc:mysql://localhost:3306/";
        dbName = "frcscout";
        driver = "com.mysql.jdbc.Driver";
        userName = "first"; 
        password = "sc0ut";
    }
    public Connection getConnection() {

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

 }