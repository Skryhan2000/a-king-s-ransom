package com.loneliness.server.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    //private Connection connection;
    private static DataBaseConnection instance ;
    private Properties properties;
    private String url;
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

            properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("password", "con2Egor");
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            url = "jdbc:mysql://localhost/a-king-s-ransom?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
            //connection = DriverManager.getConnection(url, properties);//(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch ( ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, properties);
    }
}
