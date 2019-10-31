package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.user.UserData;




public class Authorize implements Command {
    @Override
    public Object execute(Object request) throws ControllerException {
        try {
            return DAOFactory.getInstance().getUserDAO().authorize((UserData) request);
        } catch (DAOException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
