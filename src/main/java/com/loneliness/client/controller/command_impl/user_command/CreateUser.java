package com.loneliness.client.controller.command_impl.user_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.user.UserData;

import java.io.IOException;


public class CreateUser implements Command<UserData,String> {
    @Override
    public String execute(UserData request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getUserService().create(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
