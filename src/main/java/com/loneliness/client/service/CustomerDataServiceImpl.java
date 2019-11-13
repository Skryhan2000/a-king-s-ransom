package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.CustomerData;
import com.loneliness.entity.transmission.Transmission;

public class CustomerDataServiceImpl implements Service<CustomerData, Transmission>{
    @Override
    public Object create(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllCustomersDataByNameAndNumberOfOrders(CustomerData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().findAllCustomersDataByNameAndNumberOfOrders(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
