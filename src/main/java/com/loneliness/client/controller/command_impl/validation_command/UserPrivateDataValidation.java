package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.user.UserPrivateData;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UserPrivateDataValidation implements Command<UserPrivateData, Set<ConstraintViolation<UserPrivateData>>> {
    @Override
    public Set<ConstraintViolation<UserPrivateData>> execute(UserPrivateData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
