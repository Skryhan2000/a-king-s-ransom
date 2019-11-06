package com.loneliness.client.service;


public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private final UserServiceImpl userService=new UserServiceImpl();
    private final ProviderServiceImpl providerService=new ProviderServiceImpl();
    public static ServiceFactory getInstance() {
        return instance;
    }
    public ProviderServiceImpl getProviderService(){
        return providerService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }
}
