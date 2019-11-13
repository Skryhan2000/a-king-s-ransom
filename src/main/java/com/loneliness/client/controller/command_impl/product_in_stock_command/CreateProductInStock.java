package com.loneliness.client.controller.command_impl.product_in_stock_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProductInStock;

public class CreateProductInStock implements Command<ProductInStock> {
    @Override
    public Object execute(ProductInStock request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductInStockService().create(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
