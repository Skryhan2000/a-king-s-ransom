package com.loneliness.server.controller.command_impl.order_command;




import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllOrdersByDateOfCompletionAndStatus implements Command<OrderData, ConcurrentHashMap<Integer,OrderData>> {
    @Override
    public ConcurrentHashMap<Integer,OrderData> execute(OrderData request)  {
            return ServiceFactory.getInstance().getOrderService().findAllByDateOfCompletionAndStatus(request);
    }
}
