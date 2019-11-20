package com.loneliness.client.controller.command_impl.product_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.Product;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllProducts implements Command<Transmission, ConcurrentHashMap<Integer, Product>> {
    @Override
    public ConcurrentHashMap<Integer,Product>  execute(Transmission request) throws  ControllerException {
        try {
            return ServiceFactory.getInstance().getProductService().receiveAllElem(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
