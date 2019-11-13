package com.loneliness.server.controller.command_impl.user_command;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.dao.DAOFactory;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllUsers implements Command <Transmission>{
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getUserService().receiveAllElem();
    }
}
