package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.CustomerData;

public class CustomerDataServiceImpl implements Service{
    @Override
    public Object create(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllCustomersDataByNameAndNumberOfOrders(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getCustomerDataRequest().findAllCustomersDataByNameAndNumberOfOrders((CustomerData) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
