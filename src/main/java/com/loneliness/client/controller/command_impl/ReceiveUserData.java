package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.Service;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;

import java.io.IOException;

public class ReceiveUserData implements Command {
    @Override
    public Object execute(Object request) throws IOException, ClassNotFoundException, ControllerException {
        try {
            return ServiceFactory.getInstance().getUserService().receive(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
