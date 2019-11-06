package com.loneliness.client.dao;



public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final ServerUserRequest userDAO=new ServerUserRequest();
    private final ServerProviderRequest providerDAO=new ServerProviderRequest();
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
}
