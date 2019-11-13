package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

public class OrderServiceImpl implements Service{
    @Override
    public Object create(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    public Object receiveAllCustomerOrderInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllCustomerOrderInLimit((Transmission) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllOrdersByDateOfCompletionAndStatus(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().findAllOrdersByDateOfCompletionAndStatus((OrderData) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
