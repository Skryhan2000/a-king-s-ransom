package com.loneliness.server.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final SQLUserDAO userDAO=new SQLUserDAO();
    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }

    public SQLUserDAO getUserDAO() {
        return userDAO;
    }
}
