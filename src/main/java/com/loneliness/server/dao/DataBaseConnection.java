package com.loneliness.server.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    //private Connection connection;
    private static DataBaseConnection instance ;
    private Properties properties;
    private final ComboPooledDataSource comboPooledDataSource;
    public static DataBaseConnection getInstance() throws PropertyVetoException {
        if(instance==null){
            synchronized (DataBaseConnection.class){
                if(instance==null){
                    instance=new DataBaseConnection();
                }
            }
        }
        return instance;
    }

    private DataBaseConnection() throws PropertyVetoException {
        comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/a-king-s-ransom?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("con2Egor");
// the settings below are optional -- c3p0 can work with defaults
        comboPooledDataSource.setMinPoolSize(1);
        comboPooledDataSource.setAcquireIncrement(5);
        comboPooledDataSource.setMaxPoolSize(50);
    }

    public Connection getConnection() throws SQLException {
        return comboPooledDataSource.getConnection();
    }
}
