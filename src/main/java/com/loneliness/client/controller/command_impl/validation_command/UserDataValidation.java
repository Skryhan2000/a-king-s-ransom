package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.user.UserData;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UserDataValidation implements Command<UserData, Set<ConstraintViolation<UserData>>> {
    @Override
    public Set<ConstraintViolation<UserData>>  execute(UserData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
