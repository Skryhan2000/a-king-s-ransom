package com.loneless.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private Connection connection;
    private static DataBaseConnection instance ;

    public static DataBaseConnection getInstance() {
        if(instance==null){
            synchronized (DataBaseConnection.class){
                if(instance==null){
                    instance=new DataBaseConnection();
                }
            }
        }
        return instance;
    }

    private DataBaseConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("password", "con2Egor");
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            String url = "jdbc:mysql://localhost/a-king-s-ransom?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
            connection = DriverManager.getConnection(url, properties);//(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
