package com.loneliness.client.controller.command_impl.product_in_stock_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.ProductInStock;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllProductInStockByNameAndQuantity implements Command<ProductInStock, ConcurrentHashMap<Integer, ProductInStock>> {
    @Override
    public ConcurrentHashMap<Integer, ProductInStock>  execute(ProductInStock request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductInStockService().findAllProductInStockByNameAndQuantity(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
