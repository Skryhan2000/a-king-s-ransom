package com.loneliness.client.service;

import com.loneliness.client.dao.DAOException;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.client.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;


public class UserServiceImpl implements Service<UserData,Transmission,String,ConcurrentHashMap<Integer,UserData>> {
    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAllElem(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public ConcurrentHashMap<Integer,UserData> receiveAllElemInLimit(Transmission obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().receiveAllInInterval(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    public String authorization(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().authorize(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public String create(UserData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().create(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }

    @Override
    public UserData receive(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().read(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(), e.getException().toString());
        }
    }


    @Override
    public String update(UserData obj) throws   ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().update(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }

    @Override
    public String delete(UserData obj) throws  ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().delete(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
    public ConcurrentHashMap<Integer,UserData>  findByLoginAndType(UserData obj) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().findByLoginAndType(obj);
        } catch (DAOException e) {
            throw new ServiceException(e.getExceptionMessage().toString(),e.getException().toString());
        }
    }
}
