package com.loneliness.client.dao;



public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final ServerUserRequest userDAO=new ServerUserRequest();
    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }

    public ServerUserRequest getUserDAO() {
        return userDAO;
    }
}
