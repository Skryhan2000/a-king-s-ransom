package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class DeleteOrder implements Command<OrderData,String> {
    @Override
    public String execute(OrderData request) {
        return ServiceFactory.getInstance().getOrderService().delete(request);
    }
}
