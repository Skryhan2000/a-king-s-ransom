package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.OrderData;

public class OrderDataValidation implements Command {
    @Override
    public Object execute(Object request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate((OrderData) request);
    }
}
