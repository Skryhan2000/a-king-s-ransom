package com.loneliness.client.controller.command_impl.report_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.dao.DAOFactory;

public class PrintReport implements Command<String,String> {
    @Override
    public String execute(String request) throws ControllerException {
        return DAOFactory.getInstance().getReport().printReport(request);
    }
}
