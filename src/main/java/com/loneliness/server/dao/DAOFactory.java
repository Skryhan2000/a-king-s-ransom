package com.loneliness.server.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final SQLUserDAO userDAO;
    private final SQLProviderDAO providerDAO;
    private DAOFactory() {
        userDAO= new SQLUserDAO();
        providerDAO=new SQLProviderDAO();
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public SQLUserDAO getUserDAO() {
        return userDAO;
    }

    public SQLProviderDAO getProviderDAO() {
        return providerDAO;
    }
}
