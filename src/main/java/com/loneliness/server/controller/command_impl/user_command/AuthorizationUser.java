package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

public class AuthorizationUser implements Command<UserData,String> {
    @Override
    public String execute(UserData request) {
        return ServiceFactory.getInstance().getUserService().authorization(request);
    }
}
