package com.loneliness.server.controller.command_impl.customer_representative_command;

import com.loneliness.entity.CustomerRepresentative;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllCustomerRepresentativesInLimit implements Command<Transmission, ConcurrentHashMap<Integer, CustomerRepresentative>> {
    @Override
    public ConcurrentHashMap<Integer,CustomerRepresentative>  execute(Transmission request) {
        return ServiceFactory.getInstance().getCustomerRepresentativeService().receiveAllElemInLimit(request);
    }
}
