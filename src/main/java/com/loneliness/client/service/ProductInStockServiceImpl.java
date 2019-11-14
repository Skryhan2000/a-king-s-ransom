package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;

public class ProductInStockServiceImpl implements Service<ProductInStock, Transmission,String, ConcurrentHashMap<Integer, ProductInStock>>{
    @Override
    public String create(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ProductInStock receive(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String update(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String delete(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer, ProductInStock> findAllProductInStockByNameAndQuantity(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().findAllProductInStockByNameAndQuantity(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
