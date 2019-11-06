package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.client.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;


public class UserServiceImpl implements Service {
    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAllElem(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAllInInterval((Transmission) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    public Object authorization(Object obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().authorize((UserData)obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object create(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().create((UserData)obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(Object obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().read((UserData) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object update(Object obj) throws   ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().update((UserData)obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }

    @Override
    public Object delete(Object obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().delete((UserData)obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
    public Object findByLoginAndType(Object obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().findByLoginAndType((UserData) obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
