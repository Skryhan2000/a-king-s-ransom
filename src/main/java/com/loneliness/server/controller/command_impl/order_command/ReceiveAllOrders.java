package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllOrders implements Command<Transmission,ConcurrentHashMap<Integer,OrderData> > {
    @Override
    public ConcurrentHashMap<Integer, OrderData> execute(Transmission request) {
        return ServiceFactory.getInstance().getOrderService().receiveAllElem();
    }
}
