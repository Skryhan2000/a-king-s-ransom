package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.Product;
import com.loneliness.entity.orders.OrderData;

import java.math.BigDecimal;
import java.util.Collection;

public class CalculateSumOfProduct implements Command<Collection<Product>, BigDecimal> {
    @Override
    public BigDecimal execute(Collection<Product> products) throws ControllerException {
            return ServiceFactory.getInstance().getProductService().calculateSumOfProduct(products);
    }
}
