package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.orders.OrderData;

public class UpdateProduct implements Command<OrderData,String> {
    @Override
    public String execute(OrderData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getOrderService().update(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
