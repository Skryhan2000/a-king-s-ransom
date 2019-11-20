package com.loneliness.client.dao;

import com.loneliness.client.launcher.Client;
import com.loneliness.entity.Product;
import com.loneliness.entity.transmission.Transmission;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerProductRequest implements CRUD<Product, Transmission,String, ConcurrentHashMap<Integer,Product>> {
    @Override
    public String create(Product product) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_PRODUCT");
            transmission.setProductData(product);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public Product read(Product product) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_PRODUCT");
            transmission.setProductData(product);
            Client.getOutObject().writeObject(transmission);
            return (Product) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public String update(Product product) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("UPDATE_PRODUCT");
            transmission.setProductData(product);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public String delete(Product product) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("DELETE_PRODUCT");
            transmission.setProductData(product);
            Client.getOutObject().writeObject(transmission);
            return (String) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }

    }

    public ConcurrentHashMap<Integer, Product> receiveAllProductInOrderSetId(Set<Integer> set) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_CUSTOMER_PRODUCT_IN_SET_ID");
            transmission.setIntegerSet(set);
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, Product>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }

    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAll() throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_PRODUCT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, Product>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAllInInterval(Transmission transmission) throws DAOException {
        try {
            transmission.setCommand("RECEIVE_ALL_ORDERS_IN_LIMIT");
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, Product>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }

    public ConcurrentHashMap<Integer, Product> receiveProductGoods(String command) throws DAOException {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand(command);
            Client.getOutObject().writeObject(transmission);
            return (ConcurrentHashMap<Integer, Product>) Client.getInObject().readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Ошибка получения данных", "ServerOrderRequest " + e.getMessage());
        }
    }
}
