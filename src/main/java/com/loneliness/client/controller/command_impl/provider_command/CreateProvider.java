package com.loneliness.client.controller.command_impl.provider_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.client.service.Service;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.user.UserData;


public class CreateProvider implements Command {
    @Override
    public Object execute(Object request) throws  ControllerException {
        try {
            return  ServiceFactory.getInstance().getProviderService().create(request);
        } catch ( ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
