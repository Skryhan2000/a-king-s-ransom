package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class ReceiveUser implements Command<UserData,UserData> {
    @Override
    public UserData execute(UserData request) {
        return ServiceFactory.getInstance().getUserService().receive(request);
    }
}
