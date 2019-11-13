package com.loneliness.server.logic;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.dao.DAOFactory;


public class UserServiceImpl implements Service<UserData>{
    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getUserDAO().receiveAllInLimit((Transmission)obj);
    }

    public Object findAllByLoginAndType(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().findAllByLoginAndType(obj);
    }


    public Object authorization(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().receiveUserType(obj);
    }

    @Override
    public Object create(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().create( obj);
    }

    @Override
    public Object receive(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().read(obj);
    }

    @Override
    public Object update(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().update(obj);
    }

    @Override
    public Object delete(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().delete(obj);
    }


}
