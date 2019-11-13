package com.loneliness.server.logic;

import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class OrderServiceImpl implements Service<OrderData>{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getOrderDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByDateOfCompletionAndStatus(OrderData obj){
        return DAOFactory.getInstance().getOrderDAO().findAllByDateOfCompletionAndStatus(obj);
    }

    @Override
    public Object create(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().create(obj);
    }

    @Override
    public Object receive(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().read(obj);
    }

    @Override
    public Object update(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().update(obj);
    }

    @Override
    public Object delete(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().delete(obj);
    }

    public Object receiveAllCustomerOrderInLimit(Transmission obj){
        return DAOFactory.getInstance().getOrderDAO().receiveAllCustomerOrderInLimit(obj);}
}
