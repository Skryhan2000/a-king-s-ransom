package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.Product;
import com.loneliness.entity.transmission.Transmission;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProductServiceImpl implements Service<Product, Transmission,String, ConcurrentHashMap<Integer,Product>>{
    @Override
    public String create(Product obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Product receive(Product obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String update(Product obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String delete(Product obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public ConcurrentHashMap<Integer,Product> receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    public ConcurrentHashMap<Integer, Product> receiveAllCustomerOrderInSetId(Set<Integer> set) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().receiveAllProductInOrderSetId(set);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,Product> receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    public BigDecimal calculateSumOfProduct(Collection<Product> obj) {
            BigDecimal sum=new BigDecimal(0);
            for (Product product :
                    obj) {
                sum=sum.add(BigDecimal.valueOf(product.getQuantity()).multiply(BigDecimal.valueOf(product.getUnitPrice())));
            }
            return sum;
    }
    public ConcurrentHashMap<Integer, Product> receiveProductGoods(Set<Integer> command) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductRequest().receiveProductGoods(command);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
}
