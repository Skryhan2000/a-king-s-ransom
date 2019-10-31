package com.loneliness.client.service;

import com.loneliness.entity.user.UserData;
import com.loneliness.client.dao.DAOFactory;

import java.io.IOException;


public class UserServiceImpl implements Service {
    @Override
    public Object receiveAllElem(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }

    @Override
    public Object authorization(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().authorize((UserData)obj);
    }

    @Override
    public Object create(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().create((UserData)obj);
    }

    @Override
    public Object receive(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().read((UserData)obj);
    }

    @Override
    public Object update(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().update((UserData)obj);
    }

    @Override
    public Object delete(Object obj) throws IOException, ClassNotFoundException {
        return DAOFactory.getInstance().getUserDAO().delete((UserData)obj);
    }
}
