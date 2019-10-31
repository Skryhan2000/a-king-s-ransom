package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.client.service.ServiceException;
import com.loneliness.entity.user.UserData;

import java.io.IOException;


public class CreateUser implements Command {
    @Override
    public Boolean execute(Object request) throws  ControllerException {
        try {
            return DAOFactory.getInstance().getUserDAO().create((UserData) request);
        } catch (DAOException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
