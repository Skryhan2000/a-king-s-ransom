package com.loneliness.client.controller.command_impl.customer_command;


import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.CustomerData;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllCustomersDataByNameAndNumberOfOrders implements Command<CustomerData, ConcurrentHashMap<Integer,CustomerData>> {
    @Override
    public  ConcurrentHashMap<Integer,CustomerData>   execute(CustomerData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getCustomerDataService().findAllCustomersDataByNameAndNumberOfOrders(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
