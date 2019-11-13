package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.user.UserPrivateData;

public class UserPrivateDataValidation implements Command<UserPrivateData> {
    @Override
    public Object execute(UserPrivateData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
