package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProductInStock implements Command<Transmission,ConcurrentHashMap<Integer, ProductInStock>> {
    @Override
    public ConcurrentHashMap<Integer, ProductInStock> execute(Transmission request) {
        return ServiceFactory.getInstance().getProductInStockService().receiveAllElem();
    }
}
