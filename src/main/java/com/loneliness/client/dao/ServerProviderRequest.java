package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProviderRequest implements CRUD{
    @Override
    public boolean create(Object providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_PROVIDER");
            transmission.setProviderData((ProviderData) providerData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public ProviderData read(Object providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_PROVIDER");
            transmission.setProviderData((ProviderData)providerData);
            Client.getOutObject().writeObject(transmission);
            return (ProviderData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean update(Object providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_PROVIDER");
            transmission.setProviderData((ProviderData)providerData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Object providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_PROVIDER");
            transmission.setProviderData((ProviderData)providerData);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }

    }

    @Override
    public ConcurrentHashMap<Integer,ProviderData> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_PROVIDERS");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProviderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,ProviderData> receiveAllInInterval(Object transmission) throws DAOException {
        try {
            ((Transmission)transmission).setCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProviderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerUserRequest " + e.getMessage());
        }
    }
}
