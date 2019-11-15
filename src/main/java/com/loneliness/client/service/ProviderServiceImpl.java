package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.client.dao.DAOFactory;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;

import java.util.concurrent.ConcurrentHashMap;


public class ProviderServiceImpl implements Service<ProviderData, Transmission,String, ConcurrentHashMap<Integer,ProviderData>> {
    @Override
    public String create(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ProviderData receive(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String update(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String delete(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public ConcurrentHashMap<Integer,ProviderData>  receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,ProviderData>  receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer,ProviderData>  findByLocationAndRating(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().findByLocationAndRating(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer,ProviderData>  findProviderByLocationRatingAndValue(ProviderData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getProviderDAO().findProviderByLocationRatingAndValue(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }

}
