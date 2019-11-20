package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.Product;
import com.loneliness.entity.orders.OrderData;

public class DeleteProduct implements Command<Product,String> {
    @Override
    public String execute(Product request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getProductService().delete(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
