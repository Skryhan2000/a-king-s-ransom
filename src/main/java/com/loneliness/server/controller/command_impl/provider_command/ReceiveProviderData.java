package com.loneliness.server.controller.command_impl.provider_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveProviderData implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getProviderService().receive(request);
    }
}
