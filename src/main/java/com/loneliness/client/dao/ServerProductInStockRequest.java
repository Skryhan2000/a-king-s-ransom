package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.ProductInStock;

import com.loneliness.entity.transmission.Transmission;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProductInStockRequest implements CRUD{
    @Override
    public boolean create(Object productInStock) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_PRODUCT_IN_STOCK");
            transmission.setProductInStock((ProductInStock) productInStock);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }

    @Override
    public ProductInStock read(Object productInStock) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_PRODUCT_IN_STOCK");
            transmission.setProductInStock((ProductInStock)productInStock);
            Client.getOutObject().writeObject(transmission);
            return (ProductInStock) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }

    @Override
    public boolean update(Object productInStock) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_PRODUCT_IN_STOCK");
            transmission.setProductInStock((ProductInStock)productInStock);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Object productInStock) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_PRODUCT_IN_STOCK");
            transmission.setProductInStock((ProductInStock) productInStock);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }

    }

    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_PRODUCT_IN_STOCK");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProductInStock>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }
    @Override
    public ConcurrentHashMap<Integer,ProductInStock> receiveAllInInterval(Object transmission) throws DAOException {
        try {
            ((Transmission)transmission).setCommand("RECEIVE_ALL_PRODUCT_IN_STOCK_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProductInStock>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }
    public ConcurrentHashMap<Integer,ProductInStock> findAllProductInStockByNameAndQuantity(ProductInStock productInStock) throws DAOException {
        Transmission transmission=new Transmission();
        transmission.setProductInStock(productInStock);
        transmission.setCommand("FIND_ALL_PRODUCT_IN_STOCK_BY_NAME_AND_QUANTITY");
        try {
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, ProductInStock>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerProductInStockRequest " + e.getMessage());
        }
    }
}
