package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.user.UserData;

public class UserDataValidation implements Command<UserData> {
    @Override
    public Object execute(UserData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
