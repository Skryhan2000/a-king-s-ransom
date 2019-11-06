package com.loneliness.client.controller.command_impl.user_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.user.UserData;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllUsers implements Command {
    @Override
    public ConcurrentHashMap<Integer, UserData> execute(Object request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getUserService().receiveAllElem(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
