package com.loneliness.server.controller.command_impl.customer_command;

import com.loneliness.entity.CustomerData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveCustomerData implements Command<CustomerData,CustomerData> {
    @Override
    public CustomerData execute(CustomerData request) {
        return ServiceFactory.getInstance().getCustomerDataService().receive(request);
    }
}
