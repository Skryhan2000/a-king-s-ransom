package com.loneliness.server.controller.command_impl.customer_command;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ReceiveAllCustomersData implements Command<Transmission, ConcurrentHashMap<Integer, CustomerData>> {
    @Override
    public ConcurrentHashMap<Integer,CustomerData>  execute(Transmission request) {
        return ServiceFactory.getInstance().getCustomerDataService().receiveAllElem();
    }
}
