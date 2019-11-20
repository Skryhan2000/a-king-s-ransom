package com.loneliness.server.controller.command_impl.provider_command;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProviders implements Command<Transmission,ConcurrentHashMap<Integer,ProviderData>> {
    @Override
    public ConcurrentHashMap<Integer,ProviderData> execute(Transmission request) {
        return ServiceFactory.getInstance().getProviderService().receiveAllElem();
    }
}
