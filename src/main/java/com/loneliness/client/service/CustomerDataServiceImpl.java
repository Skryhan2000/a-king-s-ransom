package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class CustomerDataServiceImpl implements Service<CustomerData, Transmission,String, ConcurrentHashMap<Integer,CustomerData>>{
    @Override
    public String create(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public CustomerData receive(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String update(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String delete(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public ConcurrentHashMap<Integer,CustomerData>  receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,CustomerData>  receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer,CustomerData>  findAllCustomersDataByNameAndNumberOfOrders(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().findAllCustomersDataByNameAndNumberOfOrders(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
