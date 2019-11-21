package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.Product;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ReceiveProductGoods implements Command<Set<Integer>, ConcurrentHashMap<Integer, Product>> {
    @Override
    public ConcurrentHashMap<Integer, Product> execute(Set<Integer> request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductService().receiveProductGoods(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
