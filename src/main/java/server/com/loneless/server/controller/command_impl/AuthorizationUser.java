package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.logic.ServiceFactory;

public class AuthorizationUser implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getUserService().isSuchElemExist(request);
    }
}
