package com.loneliness.client.controller.command_impl.validation_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.DataValidationFactory;
import com.loneliness.entity.orders.OrderData;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class OrderDataValidation implements Command <OrderData, Set<ConstraintViolation<OrderData>>>{
    @Override
    public Set<ConstraintViolation<OrderData>>  execute(OrderData request) throws ControllerException {
        return DataValidationFactory.getValidatorFactory().validate(request);
    }
}
