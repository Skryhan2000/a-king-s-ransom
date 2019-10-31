package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.dao.DAOFactory;

public class ReceiveAllUsers implements Command {
    @Override
    public Object execute(Object request) {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }
}
