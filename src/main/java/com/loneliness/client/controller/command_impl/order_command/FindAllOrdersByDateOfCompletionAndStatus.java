package com.loneliness.client.controller.command_impl.order_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.orders.OrderData;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllOrdersByDateOfCompletionAndStatus implements Command<OrderData, ConcurrentHashMap<Integer,OrderData>> {
    @Override
    public  ConcurrentHashMap<Integer,OrderData>  execute(OrderData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getOrderService().findAllOrdersByDateOfCompletionAndStatus(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
}
