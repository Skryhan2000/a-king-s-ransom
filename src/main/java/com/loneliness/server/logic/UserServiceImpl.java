package com.loneliness.server.logic;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.dao.DAOFactory;


public class UserServiceImpl implements Service{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getUserDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByLoginAndType(Object obj) {
        return DAOFactory.getInstance().getUserDAO().findAllByLoginAndType((UserData)obj);
    }


    public Object authorization(Object obj) {
        return DAOFactory.getInstance().getUserDAO().receiveUserType((UserData)obj);
    }

    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getUserDAO().create(obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getUserDAO().read(obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getUserDAO().update(obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getUserDAO().delete(obj);
    }


}
