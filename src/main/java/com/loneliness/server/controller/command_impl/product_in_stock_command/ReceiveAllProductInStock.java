package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllProductInStock implements Command<Transmission> {
    @Override
    public Object execute(Transmission request) {
        return ServiceFactory.getInstance().getProductInStockService().receiveAllElem();
    }
}
