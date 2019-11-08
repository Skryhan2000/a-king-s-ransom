package com.loneliness.server.controller.command_impl.order_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class DeleteOrder implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getOrderService().delete(request);
    }
}
