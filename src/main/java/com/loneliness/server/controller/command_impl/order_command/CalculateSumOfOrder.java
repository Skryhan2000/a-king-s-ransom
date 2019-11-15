package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

import java.math.BigDecimal;

public class CalculateSumOfOrder implements Command<OrderData, BigDecimal> {
    @Override
    public BigDecimal execute(OrderData request) {
        return ServiceFactory.getInstance().getOrderService().calculateSumOfOrder(request);
    }
}
