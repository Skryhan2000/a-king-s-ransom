package com.loneliness.server.logic;




public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private final UserServiceImpl userService=new UserServiceImpl();
    private final ProviderServiceImpl providerService=new ProviderServiceImpl();
    private final OrderServiceImpl orderService=new OrderServiceImpl();
    private final ProductInStockServiceImpl productInStockService = new ProductInStockServiceImpl();
    private final CustomerDataServiceImpl customerDataService=new CustomerDataServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public ProviderServiceImpl getProviderService() {
        return providerService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public ProductInStockServiceImpl getProductInStockService() {
        return productInStockService;
    }

    public CustomerDataServiceImpl getCustomerDataService() {
        return customerDataService;
    }
}
