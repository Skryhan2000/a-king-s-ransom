package com.loneliness.server.controller.command_impl.customer_command;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllCustomersDataInLimit implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getCustomerDataService().receiveAllElemInLimit(request);
    }
}
