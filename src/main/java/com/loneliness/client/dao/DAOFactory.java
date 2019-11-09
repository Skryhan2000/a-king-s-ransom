package com.loneliness.client.dao;



public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final ServerUserRequest userDAO=new ServerUserRequest();
    private final ServerProviderRequest providerDAO=new ServerProviderRequest();
    private final ServerOrderRequest orderRequest=new ServerOrderRequest();
    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }
    public ServerProviderRequest getProviderDAO() {
        return providerDAO;
    }
    public ServerUserRequest getUserDAO() {
        return userDAO;
    }

    public ServerOrderRequest getOrderRequest() {
        return orderRequest;
    }
}
