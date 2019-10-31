package com.loneliness.client.service;

import com.loneliness.entity.user.UserData;
import com.loneliness.server.dao.DAOFactory;


public class UserServiceImpl implements Service {
    @Override
    public Object receiveAllElem(Object obj) {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }

    @Override
    public Object authorization(Object obj) {
        return DAOFactory.getInstance().getUserDAO().receiveUserType((UserData)obj);
    }

    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getUserDAO().create((UserData)obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getUserDAO().read((UserData)obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getUserDAO().update((UserData)obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getUserDAO().delete((UserData)obj);
    }
}
