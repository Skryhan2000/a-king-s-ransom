package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class OrderServiceImpl implements Service<OrderData,Transmission,String, ConcurrentHashMap<Integer,OrderData>>{
    @Override
    public String create(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public OrderData receive(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String update(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String delete(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    public ConcurrentHashMap<Integer, OrderCustomerData> receiveAllCustomerOrderInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllCustomerOrderInLimit(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer,OrderData> findAllOrdersByDateOfCompletionAndStatus(OrderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getOrderRequest().findAllOrdersByDateOfCompletionAndStatus(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
