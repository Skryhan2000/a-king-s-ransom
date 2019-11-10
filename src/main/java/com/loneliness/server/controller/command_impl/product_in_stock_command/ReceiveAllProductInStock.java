package com.loneliness.server.controller.command_impl.product_in_stock_command;

import com.loneliness.server.controller.Command;
import com.loneliness.server.logic.ServiceFactory;

public class ReceiveAllProductInStock implements Command {
    @Override
    public Object execute(Object request) {
        return ServiceFactory.getInstance().getProductInStockService().receiveAllElem();
    }
}
