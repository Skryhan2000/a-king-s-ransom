package com.loneliness.client.controller.command_impl.Validation;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.ProviderData;

import javax.validation.Validator;

public class ProviderValidation implements Command {
    @Override
    public Object execute(Object request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate((ProviderData)request);
    }
}
