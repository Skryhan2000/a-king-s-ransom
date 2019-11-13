package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class FindAllByLoginAndType implements Command<UserData> {
    @Override
    public Object execute(UserData request) {
        return ServiceFactory.getInstance().getUserService().findAllByLoginAndType(request);
    }
}
