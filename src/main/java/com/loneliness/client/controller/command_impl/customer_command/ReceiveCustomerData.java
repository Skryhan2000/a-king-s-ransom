package com.loneliness.client.controller.command_impl.customer_command;


import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.CustomerData;

public class ReceiveCustomerData implements Command<CustomerData,CustomerData> {
    @Override
    public CustomerData execute(CustomerData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getCustomerDataService().receive(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
