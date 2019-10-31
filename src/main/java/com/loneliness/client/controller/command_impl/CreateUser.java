package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.user.UserData;


public class CreateUser implements Command {
    @Override
    public Boolean execute(Object request) {
        return DAOFactory.getInstance().getUserDAO().create((UserData) request);
    }
}
