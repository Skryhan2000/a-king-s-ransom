package com.loneliness.server.logic;

import com.loneliness.entity.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.dao.DAOFactory;

public class OrderServiceImpl implements Service{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getOrderDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByDateOfCompletionAndStatus(Object obj){
        return DAOFactory.getInstance().getOrderDAO().findAllByDateOfCompletionAndStatus((OrderData)obj);
    }

    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().create(obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().read(obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().update(obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getOrderDAO().delete(obj);
    }
}
