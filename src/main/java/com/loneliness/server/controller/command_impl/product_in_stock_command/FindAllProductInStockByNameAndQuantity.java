package com.loneliness.server.controller.command_impl.product_in_stock_command;




import com.loneliness.entity.ProductInStock;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllProductInStockByNameAndQuantity implements Command<ProductInStock, ConcurrentHashMap<Integer, ProductInStock>> {
    @Override
    public ConcurrentHashMap<Integer, ProductInStock> execute(ProductInStock request)  {
            return ServiceFactory.getInstance().getProductInStockService().findAllByNameAndQuantity(request);
    }
}
