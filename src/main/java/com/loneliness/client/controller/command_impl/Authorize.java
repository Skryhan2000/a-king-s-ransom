package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.user.UserData;


public class Authorize implements Command {
    @Override
    public Object execute(Object request) {
        return DAOFactory.getInstance().getUserDAO().authorize((UserData) request);
    }
}
