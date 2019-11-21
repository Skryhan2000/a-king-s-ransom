package com.loneliness.server.controller.command_impl.customer_representative_command;

import com.loneliness.entity.CustomerRepresentative;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class ReceiveCustomerRepresentativeData implements Command<CustomerRepresentative,CustomerRepresentative> {
    @Override
    public CustomerRepresentative execute(CustomerRepresentative request) {
        return ServiceFactory.getInstance().getCustomerRepresentativeService().receive(request);
    }
}
