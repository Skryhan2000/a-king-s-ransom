package com.loneliness.server.controller.command_impl.customer_command;




import com.loneliness.entity.CustomerData;
import com.loneliness.server.controller.Command;
import com.loneliness.server.service.ServiceFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FindAllCustomersDataByNameAndNumberOfOrders implements Command<CustomerData, ConcurrentHashMap<Integer,CustomerData>> {
    @Override
    public ConcurrentHashMap<Integer,CustomerData>  execute(CustomerData request)  {
            return ServiceFactory.getInstance().getCustomerDataService().findAllByNameAndNumberOfOrders(request);
    }
}
