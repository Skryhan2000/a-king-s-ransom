package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

public class OrderServiceImpl implements Service<OrderData,Transmission>{
    @Override
    public Object create(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    public Object receiveAllCustomerOrderInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllCustomerOrderInLimit(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllOrdersByDateOfCompletionAndStatus(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().findAllOrdersByDateOfCompletionAndStatus(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
