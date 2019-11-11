package com.loneliness.server.dao;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final SQLUserDAO userDAO;
    private final SQLProviderDAO providerDAO;
    private final SQLOrderDAO orderDAO;
    private final SQLProductInStockDAO productInStockDAO;
    private final SQLCustomerDataDAO customerDataDAO;
    private DAOFactory() {
        userDAO= new SQLUserDAO();
        providerDAO=new SQLProviderDAO();
        orderDAO=new SQLOrderDAO();
        productInStockDAO=new SQLProductInStockDAO();
        customerDataDAO=new SQLCustomerDataDAO();
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

    public SQLOrderDAO getOrderDAO() {
        return orderDAO;
    }

    public SQLCustomerDataDAO getCustomerDataDAO() {
        return customerDataDAO;
    }

    public SQLProductInStockDAO getProductInStockDAO() {
        return productInStockDAO;
    }
}
