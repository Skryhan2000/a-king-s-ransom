package com.loneliness.server.service;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;

public class CustomerDataServiceImpl implements Service<CustomerData, ConcurrentHashMap<Integer,CustomerData>,String,Transmission>{
    @Override
    public ConcurrentHashMap<Integer,CustomerData> receiveAllElem() {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer,CustomerData> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().receiveAllInLimit(obj);
    }

    public ConcurrentHashMap<Integer,CustomerData>  findAllByNameAndNumberOfOrders(CustomerData obj){
        return DAOFactory.getInstance().getCustomerDataDAO().findAllByNameAndNumberOfOrders(obj);
    }

    @Override
    public String create(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().create(obj);
    }

    @Override
    public CustomerData receive(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().read(obj);
    }

    @Override
    public String update(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().update(obj);
    }

    @Override
    public String delete(CustomerData obj) {
        return DAOFactory.getInstance().getCustomerDataDAO().delete(obj);
    }
}
