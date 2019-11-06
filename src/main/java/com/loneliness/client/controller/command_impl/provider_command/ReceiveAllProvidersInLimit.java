package com.loneliness.client.controller.command_impl.provider_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;

public class ReceiveAllProvidersInLimit implements Command {
    @Override
    public Object execute(Object request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProviderService().receiveAllElemInLimit(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
