package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllUsers implements Command <Transmission, ConcurrentHashMap<Integer, UserData>>{
    @Override
    public ConcurrentHashMap<Integer, UserData> execute(Transmission request) {
        return ServiceFactory.getInstance().getUserService().receiveAllElem();
    }
}
