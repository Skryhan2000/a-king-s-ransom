package com.loneliness.server.controller.command_impl.customer_representative_command;

import com.loneliness.entity.CustomerRepresentative;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class UpdateCustomerRepresentative implements Command<CustomerRepresentative,String>{
    @Override
    public String execute(CustomerRepresentative request) {
        return ServiceFactory.getInstance().getCustomerRepresentativeService().update(request);
    }
}
