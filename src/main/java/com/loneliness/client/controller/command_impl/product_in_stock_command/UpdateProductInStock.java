package com.loneliness.client.controller.command_impl.product_in_stock_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.ProviderData;

public class UpdateProductInStock implements Command<ProductInStock> {
    @Override
    public Object execute(ProductInStock request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductInStockService().update(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
