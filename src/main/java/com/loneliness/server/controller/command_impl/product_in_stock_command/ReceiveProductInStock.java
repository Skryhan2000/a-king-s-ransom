package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.entity.ProductInStock;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveProductInStock implements Command<ProductInStock,ProductInStock> {
    @Override
    public ProductInStock execute(ProductInStock request) {
        return ServiceFactory.getInstance().getProductInStockService().receive(request);
    }
}
