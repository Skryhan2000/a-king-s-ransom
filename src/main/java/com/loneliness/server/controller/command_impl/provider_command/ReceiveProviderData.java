package com.loneliness.server.controller.command_impl.provider_command;

import com.loneliness.entity.ProviderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveProviderData implements Command<ProviderData, ProviderData> {
    @Override
    public ProviderData execute(ProviderData request) {
        return ServiceFactory.getInstance().getProviderService().receive(request);
    }
}
