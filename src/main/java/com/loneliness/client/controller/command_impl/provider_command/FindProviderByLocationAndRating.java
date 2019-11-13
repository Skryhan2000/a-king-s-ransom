package com.loneliness.client.controller.command_impl.provider_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProviderData;

public class FindProviderByLocationAndRating implements Command<ProviderData> {
    @Override
    public Object execute(ProviderData request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProviderService().findByLocationAndRating(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
}
