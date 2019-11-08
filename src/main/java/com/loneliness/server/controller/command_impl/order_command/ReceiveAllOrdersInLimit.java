package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllOrdersInLimit implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getOrderService().receiveAllElemInLimit(request);
    }
}
