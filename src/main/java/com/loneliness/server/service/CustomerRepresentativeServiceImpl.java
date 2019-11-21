package com.loneliness.server.service;

import com.loneliness.entity.CustomerRepresentative;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;

public class CustomerRepresentativeServiceImpl implements Service<CustomerRepresentative, ConcurrentHashMap<Integer, CustomerRepresentative>,String, Transmission>{
    @Override
    public String create(CustomerRepresentative obj) {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().create(obj);
    }

    @Override
    public CustomerRepresentative receive(CustomerRepresentative obj) {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().read(obj);
    }

    @Override
    public String update(CustomerRepresentative obj) {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().update(obj);
    }

    @Override
    public String delete(CustomerRepresentative obj) {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().delete(obj);
    }

    @Override
    public ConcurrentHashMap<Integer, CustomerRepresentative> receiveAllElem() {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer, CustomerRepresentative> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getCustomerRepresentativeDAO().receiveAllInLimit(obj);
    }
}
