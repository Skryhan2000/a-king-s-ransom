package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.service.ServiceFactory;

import java.io.IOException;

public class ReceiveAllUsers implements Command {
    @Override
    public Object execute(Object request) throws IOException, ClassNotFoundException {
        return ServiceFactory.getInstance().getUserService().receiveAllElem(request);
    }
}
