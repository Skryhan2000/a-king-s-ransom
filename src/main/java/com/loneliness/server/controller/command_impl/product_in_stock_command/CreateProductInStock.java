package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.entity.ProductInStock;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class CreateProductInStock implements Command<ProductInStock,String> {
    @Override
    public String execute(ProductInStock request) {
        return ServiceFactory.getInstance().getProductInStockService().create(request);
    }
}
