package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.dao.DAOFactory;

public class ReceiveAllUsers implements Command {
    @Override
    public Object execute(Object request) {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }
}
