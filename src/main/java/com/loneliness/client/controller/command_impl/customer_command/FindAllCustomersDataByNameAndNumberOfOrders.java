package com.loneliness.client.controller.command_impl.customer_command;


import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.CustomerData;

public class FindAllCustomersDataByNameAndNumberOfOrders implements Command<CustomerData> {
    @Override
    public Object execute(CustomerData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getCustomerDataService().findAllCustomersDataByNameAndNumberOfOrders(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
