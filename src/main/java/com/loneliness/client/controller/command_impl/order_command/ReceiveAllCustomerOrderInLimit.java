package com.loneliness.client.controller.command_impl.order_command;


import com.loneliness.client.controller.Command;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.service.ServiceException;
import com.loneliness.client.service.ServiceFactory;
import com.loneliness.entity.transmission.Transmission;

public class ReceiveAllCustomerOrderInLimit implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) throws ControllerException {
        try {
            return ServiceFactory.getInstance().getOrderService().receiveAllCustomerOrderInLimit(request);
        } catch (ServiceException e) {
            throw new ControllerException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
