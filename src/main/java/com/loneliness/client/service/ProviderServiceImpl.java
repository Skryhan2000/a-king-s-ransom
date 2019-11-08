package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProviderData;

public class ProviderServiceImpl implements Service{
    @Override
    public Object create(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findByLocationAndRating(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().findByLocationAndRating((ProviderData) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
