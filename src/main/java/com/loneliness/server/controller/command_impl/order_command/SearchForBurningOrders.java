package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class SearchForBurningOrders implements Command<OrderData, ConcurrentHashMap<Integer, OrderData>> {
    @Override
    public ConcurrentHashMap<Integer, OrderData> execute(OrderData request) {
        return ServiceFactory.getInstance().getOrderService().searchForBurningOrders(request);
    }
}
