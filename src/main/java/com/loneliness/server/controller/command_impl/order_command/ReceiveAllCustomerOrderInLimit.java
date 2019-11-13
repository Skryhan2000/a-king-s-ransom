package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.dao.DAOFactory;
import com.loneliness.server.logic.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllCustomerOrderInLimit implements Command<Transmission, ConcurrentHashMap<Integer, OrderCustomerData>> {
    @Override
    public  ConcurrentHashMap<Integer, OrderCustomerData> execute(Transmission request) {
        return ServiceFactory.getInstance().getOrderService().receiveAllCustomerOrderInLimit(request);
    }
}
