package com.loneliness.server.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final SQLUserDAO userDAO=new SQLUserDAO();
    private final SQLProviderDAO providerDAO=new SQLProviderDAO();

    private DAOFactory() {
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
