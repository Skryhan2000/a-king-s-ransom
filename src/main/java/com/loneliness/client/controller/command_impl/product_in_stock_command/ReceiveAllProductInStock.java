package com.loneliness.client.controller.command_impl.product_in_stock_command;

import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.transmission.Transmission;

public class ReceiveAllProductInStock implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getProductInStockService().receiveAllElem(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
