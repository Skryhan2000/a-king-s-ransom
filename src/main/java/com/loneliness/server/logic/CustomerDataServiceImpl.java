package com.loneliness.server.logic;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class CustomerDataServiceImpl implements Service<CustomerData>{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByNameAndNumberOfOrders(CustomerData obj){
        return DAOFactory.getInstance().getCustomerDataDAO().findAllByNameAndNumberOfOrders(obj);
    }

    @Override
    public Object create(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().create(obj);
    }

    @Override
    public Object receive(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().read(obj);
    }

    @Override
    public Object update(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().update(obj);
    }

    @Override
    public Object delete(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().delete(obj);
    }
}
