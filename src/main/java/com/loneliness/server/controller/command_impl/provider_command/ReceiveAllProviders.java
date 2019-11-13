package com.loneliness.server.controller.command_impl.provider_command;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllProviders implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getProviderService().receiveAllElem();
    }
}
