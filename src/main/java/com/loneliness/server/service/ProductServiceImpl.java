package com.loneliness.server.service;

import com.loneliness.entity.Product;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProductServiceImpl implements Service<Product, ConcurrentHashMap<Integer, Product>,String, Transmission>{
    @Override
    public String create(Product obj) {
        return DAOFactory.getInstance().getProductDAO().create(obj);
    }

    @Override
    public Product receive(Product obj) {
        return DAOFactory.getInstance().getProductDAO().read(obj);
    }

    @Override
    public String update(Product obj) {
        return DAOFactory.getInstance().getProductDAO().update(obj);
    }

    @Override
    public String delete(Product obj) {
        return DAOFactory.getInstance().getProductDAO().delete(obj);
    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAllElem() {
        return DAOFactory.getInstance().getProductDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getProductDAO().receiveAllInLimit(obj);
    }
    public ConcurrentHashMap<Integer, Product> receiveAllProductInSetOrderId(Set<Integer> col) {
        return DAOFactory.getInstance().getProductDAO().receiveAllProductInSetOrderId(col);
    }
}
