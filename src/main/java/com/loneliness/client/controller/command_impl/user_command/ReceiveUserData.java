package com.loneliness.client.controller.command_impl.user_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.user.UserData;

public class ReceiveUserData implements Command<UserData,UserData> {
    @Override
    public UserData execute(UserData request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getUserService().receive(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
