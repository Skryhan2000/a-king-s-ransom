package com.loneliness.server.controller.command_impl.customer_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllCustomersDataInLimit implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getCustomerDataService().receiveAllElemInLimit(request);
    }
}
