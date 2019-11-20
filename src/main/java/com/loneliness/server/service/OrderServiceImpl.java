package com.loneliness.server.service;

import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class OrderServiceImpl implements Service<OrderData,ConcurrentHashMap<Integer,OrderData>,String,Transmission>{
    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAllElem() {
        return DAOFactory.getInstance().getOrderDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getOrderDAO().receiveAllInLimit(obj);
    }

    public  ConcurrentHashMap<Integer,OrderData> findAllByDateOfCompletionAndStatus(OrderData obj){
        return DAOFactory.getInstance().getOrderDAO().findAllByDateOfCompletionAndStatus(obj);
    }

    @Override
    public String create(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().create(obj);
    }

    @Override
    public OrderData receive(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().read(obj);
    }

    @Override
    public String update(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().update(obj);
    }

    @Override
    public String delete(OrderData obj) {
        return DAOFactory.getInstance().getOrderDAO().delete(obj);
    }

    public ConcurrentHashMap<Integer, OrderCustomerData> receiveAllCustomerOrderInLimit(Transmission obj){
        return DAOFactory.getInstance().getOrderDAO().receiveAllCustomerOrderInLimitByClientId(obj);
    }
    public  ConcurrentHashMap<Integer, OrderData> searchForBurningOrders(OrderData orderData){
        return DAOFactory.getInstance().getOrderDAO().searchForBurningOrders(orderData);
    }
    public BigDecimal calculateSumOfOrder(OrderData orderData){
        return DAOFactory.getInstance().getOrderDAO().calculateSumOfOrder(orderData);
    }

}
