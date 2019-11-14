package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.ProviderData;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ProviderValidation implements Command<ProviderData, Set<ConstraintViolation<ProviderData>>> {
    @Override
    public Set<ConstraintViolation<ProviderData>>  execute(ProviderData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
