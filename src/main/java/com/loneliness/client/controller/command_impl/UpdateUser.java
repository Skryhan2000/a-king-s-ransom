package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.service.Service;
import com.loneliness.client.service.ServiceFactory;

public class UpdateUser implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getUserService().update(request);
    }
}
