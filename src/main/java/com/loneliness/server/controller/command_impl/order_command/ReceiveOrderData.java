package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveOrderData implements Command<OrderData,OrderData> {
    @Override
    public OrderData execute(OrderData request) {
        return ServiceFactory.getInstance().getOrderService().receive(request);
    }
}
