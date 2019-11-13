package com.loneliness.server.logic;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class CustomerDataServiceImpl implements Service{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByNameAndNumberOfOrders(Object obj){
        return DAOFactory.getInstance().getCustomerDataDAO().findAllByNameAndNumberOfOrders((CustomerData) obj);
    }

    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().create(obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().read(obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().update(obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().delete(obj);
    }
}
