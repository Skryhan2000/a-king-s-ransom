package com.loneliness.server.controller.command_impl.user_command;


import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.Map;

public class DeleteManagerEmail implements Command<Map<Integer,String>,String> {
    @Override
    public String execute(Map<Integer, String> request)  {
        return ServiceFactory.getInstance().getUserService().deleteManagerEmail(request);
    }
}
