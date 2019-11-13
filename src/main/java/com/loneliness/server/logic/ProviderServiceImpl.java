package com.loneliness.server.logic;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

public class ProviderServiceImpl implements Service<ProviderData>{
    @Override
    public Object create(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().create(obj);
    }

    @Override
    public Object receive(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().read(obj);
    }

    @Override
    public Object update(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().update(obj);
    }

    @Override
    public Object delete(ProviderData obj) {
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
    public Object findAllByLocationAndRating(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().findAllByLocationAndRating( obj);
    }
}
