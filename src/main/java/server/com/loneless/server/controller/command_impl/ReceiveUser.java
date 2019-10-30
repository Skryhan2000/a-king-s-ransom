package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.logic.ServiceFactory;

public class ReceiveUser implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getUserService().receive(request);
    }
}
