package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProviderRequest implements CRUD<ProviderData,Transmission,String,ConcurrentHashMap<Integer,ProviderData>>{
    @Override
    public String create(ProviderData providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_PROVIDER");
            transmission.setProviderData(providerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }

    @Override
    public ProviderData read(ProviderData providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_PROVIDER");
            transmission.setProviderData(providerData);
            Client.getOutObject().writeObject(transmission);
            return (ProviderData) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }

    @Override
    public String update(ProviderData providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_PROVIDER");
            transmission.setProviderData(providerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }

    @Override
    public String delete(ProviderData providerData) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_PROVIDER");
            transmission.setProviderData(providerData);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
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
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,ProviderData> receiveAllInInterval(Transmission transmission) throws DAOException {
        try {
            transmission.setCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProviderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }
    public ConcurrentHashMap<Integer,ProviderData> findByLocationAndRating(ProviderData providerData) throws DAOException {
        Transmission transmission=new Transmission();
        transmission.setProviderData(providerData);
        transmission.setCommand("FIND_PROVIDER_BY_LOCATION_AND_RATING");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProviderData>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProviderRequest " + e.getMessage());
        }
    }
}
