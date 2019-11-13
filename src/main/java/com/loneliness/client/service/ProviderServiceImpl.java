package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;



public class ProviderServiceImpl implements Service<ProviderData, Transmission>{
    @Override
    public Object create(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object update(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object delete(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public Object findByLocationAndRating(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().findByLocationAndRating(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
