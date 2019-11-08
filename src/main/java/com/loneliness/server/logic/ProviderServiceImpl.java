package com.loneliness.server.logic;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class ProviderServiceImpl implements Service{
    @Override
    public Object create(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().create(obj);
    }

    @Override
    public Object receive(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().read(obj);
    }

    @Override
    public Object update(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().update(obj);
    }

    @Override
    public Object delete(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().delete(obj);
    }

    @Override
    public Object receiveAllElem() {
        return DAOFactory.getInstance().getProviderDAO().receiveAll();
    }

    @Override
    public Object receiveAllElemInLimit(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().receiveAllInLimit((Transmission) obj);
    }
    public Object findAllByLocationAndRating(Object obj) {
        return DAOFactory.getInstance().getProviderDAO().findAllByLocationAndRating((ProviderData) obj);
    }
}
