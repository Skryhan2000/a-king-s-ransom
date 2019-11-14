package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.CustomerData;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CustomerDataValidation implements Command<CustomerData, Set<ConstraintViolation<CustomerData>>> {
    @Override
    public Set<ConstraintViolation<CustomerData>> execute(CustomerData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
