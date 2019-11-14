package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.ProductInStock;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class ProductInStockValidation implements Command<ProductInStock, Set<ConstraintViolation<ProductInStock>>> {
    @Override
    public Set<ConstraintViolation<ProductInStock>>  execute(ProductInStock request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
