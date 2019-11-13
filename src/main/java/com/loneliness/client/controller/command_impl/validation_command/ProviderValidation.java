package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.ProviderData;

public class ProviderValidation implements Command<ProviderData> {
    @Override
    public Object execute(ProviderData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
