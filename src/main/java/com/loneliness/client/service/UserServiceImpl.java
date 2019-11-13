package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.client.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;


public class UserServiceImpl implements Service<UserData,Transmission> {
    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    public Object authorization(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().authorize(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object create(UserData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public Object receive(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public Object update(UserData obj) throws   ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }

    @Override
    public Object delete(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
    public Object findByLoginAndType(UserData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().findByLoginAndType(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
