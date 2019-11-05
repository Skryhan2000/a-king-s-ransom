package com.loneliness.server.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final SQLUserDAO userDAO=new SQLUserDAO();


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public SQLUserDAO getUserDAO() {
        return userDAO;
    }
}
