package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.Product;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProductsInLimit implements Command<Transmission, ConcurrentHashMap<Integer, Product>> {
    @Override
    public ConcurrentHashMap<Integer,Product>  execute(Transmission request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductService().receiveAllElemInLimit(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
