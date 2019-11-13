package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.transmission.Transmission;

public class ProductInStockServiceImpl implements Service<ProductInStock, Transmission>{
    @Override
    public Object create(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findAllProductInStockByNameAndQuantity(ProductInStock obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProductInStockRequest().findAllProductInStockByNameAndQuantity(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
