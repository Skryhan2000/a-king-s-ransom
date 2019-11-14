package com.loneliness.client.controller.command_impl.report_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;

public class CreateReport implements Command<String,String> {
    @Override
    public String execute(String request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getReportLogic().createReport(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
