package com.loneliness.server.controller.command_impl.provider_command;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllProvidersInLimit implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getProviderService().receiveAllElemInLimit(request);
    }
}
