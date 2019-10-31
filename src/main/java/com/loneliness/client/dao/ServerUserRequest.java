package com.loneliness.client.dao;

import com.loneliness.entity.user.UserData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.client.launcher.Client;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerUserRequest implements CRUD {
    @Override
    public boolean create(UserData user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_USER");
            transmission.setUserData(user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public UserData read(UserData user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_USER");
            transmission.setUserData(user);
            Client.getOutObject().writeObject(transmission);
            return (UserData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean update(UserData user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_USER");
            transmission.setUserData(user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UserData user) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_USER");
            transmission.setUserData(user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }

    }

    public String authorize(UserData userData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("AUTHORIZATION_USER");
            transmission.setUserData(userData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public ConcurrentHashMap receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_USERS");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
}
