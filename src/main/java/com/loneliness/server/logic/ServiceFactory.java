package com.loneliness.server.logic;




public class ServiceFactory {
    private static final ServiceFactory instance=new ServiceFactory();
    private final UserServiceImpl userService=new UserServiceImpl();
    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }
}
