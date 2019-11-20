package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.CustomerData;
import com.loneliness.entity.Product;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ProductDataValidation implements Command<Product, Set<ConstraintViolation<Product>>> {
    @Override
    public Set<ConstraintViolation<Product>> execute(Product request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
