package com.loneless.client.server_request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

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


    private String url = "jdbc:mysql://localhost/vase?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF-8";//"jdbc:mysql://localhost/vase?serverTimezone=Europe/Moscow&useSSL=false";
    private String username = "root";
    private String password = "con2Egor";
    private Properties properties;
    private Connection connection;

    private DataBaseConnection() {
        try {
            properties=new Properties();
            properties.setProperty("user",username);
            properties.setProperty("password", password);
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            connection = DriverManager.getConnection(url, properties);//(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
