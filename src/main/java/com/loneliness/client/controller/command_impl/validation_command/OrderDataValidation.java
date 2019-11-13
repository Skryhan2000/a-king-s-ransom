package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.orders.OrderData;

public class OrderDataValidation implements Command <OrderData>{
    @Override
    public Object execute(OrderData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
