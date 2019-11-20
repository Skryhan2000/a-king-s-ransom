package com.loneliness.server.controller.command_impl.product_command;

import com.loneliness.entity.Product;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProductInSetOrderId implements Command<Set<Integer>, ConcurrentHashMap<Integer, Product>> {
    @Override
    public ConcurrentHashMap<Integer, Product> execute(Set<Integer> request) {
        return ServiceFactory.getInstance().getProductService().receiveAllProductInSetOrderId(request);
    }
}
