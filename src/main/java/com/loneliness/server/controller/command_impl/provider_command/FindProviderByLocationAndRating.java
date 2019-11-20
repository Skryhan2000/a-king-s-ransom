package com.loneliness.server.controller.command_impl.provider_command;




import com.loneliness.entity.ProviderData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FindProviderByLocationAndRating implements Command<ProviderData, ConcurrentHashMap<Integer,ProviderData>> {
    @Override
    public ConcurrentHashMap<Integer,ProviderData> execute(ProviderData request)  {
            return ServiceFactory.getInstance().getProviderService().findAllByLocationAndRating(request);
    }
}
