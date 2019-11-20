package com.loneliness.server.controller.command_impl.product_command;

import com.loneliness.entity.Product;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProductInLimit implements Command<Transmission, ConcurrentHashMap<Integer, Product>> {
    @Override
    public ConcurrentHashMap<Integer, Product> execute(Transmission request) {
        return ServiceFactory.getInstance().getProductService().receiveAllElemInLimit(request);
    }
}
