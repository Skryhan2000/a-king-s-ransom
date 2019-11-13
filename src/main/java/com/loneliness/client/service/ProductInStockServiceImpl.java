package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProductInStock;

public class ProductInStockServiceImpl implements Service{
    @Override
    public Object create(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllProductInStockByNameAndQuantity(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().findAllProductInStockByNameAndQuantity((ProductInStock) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
