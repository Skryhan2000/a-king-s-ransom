package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.entity.ProductInStock;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class CreateProductInStock implements Command<ProductInStock> {
    @Override
    public Object execute(ProductInStock request) {
        return ServiceFactory.getInstance().getProductInStockService().create(request);
    }
}
