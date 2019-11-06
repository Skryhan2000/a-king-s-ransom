package com.loneliness.server.logic;




public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private final UserServiceImpl userService=new UserServiceImpl();
    private final ProviderServiceImpl providerService=new ProviderServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public ProviderServiceImpl getProviderService() {
        return providerService;
    }
}
