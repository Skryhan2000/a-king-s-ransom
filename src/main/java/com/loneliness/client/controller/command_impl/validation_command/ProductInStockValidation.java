package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.ProductInStock;


public class ProductInStockValidation implements Command<ProductInStock> {
    @Override
    public Object execute(ProductInStock request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
