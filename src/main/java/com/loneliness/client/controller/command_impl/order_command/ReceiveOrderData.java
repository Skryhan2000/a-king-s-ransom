package com.loneliness.client.controller.command_impl.order_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;

public class ReceiveOrderData implements Command {
    @Override
    public Object execute(Object request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getOrderService().receive(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
