package com.loneliness.client.dao;



public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final ServerUserRequest userDAO=new ServerUserRequest();
    private final ServerProviderRequest providerDAO=new ServerProviderRequest();
    private final ServerOrderRequest orderRequest=new ServerOrderRequest();
    private final ServerProductInStockRequest productInStockRequest=new ServerProductInStockRequest();
    private final ServerCustomerDataRequest customerDataRequest=new ServerCustomerDataRequest();
    private final ReportDAO report=new ReportDAO();
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
    public ServerProductInStockRequest getProductInStockRequest() {
        return productInStockRequest;
    }

    public ServerCustomerDataRequest getCustomerDataRequest() {
        return customerDataRequest;
    }

    public ServerOrderRequest getOrderRequest() {
        return orderRequest;
    }

    public ReportDAO getReport() {
        return report;
    }
}
