package com.loneliness.client.controller.command_impl.order_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.orders.OrderData;

import java.math.BigDecimal;

public class CalculateSumOfOrder implements Command<OrderData, BigDecimal> {
    @Override
    public BigDecimal execute(OrderData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getOrderService().calculateSumOfOrder(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
