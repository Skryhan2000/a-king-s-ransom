package com.loneliness.server.logic;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.dao.DAOFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ProviderServiceImpl implements Service<ProviderData, ConcurrentHashMap<Integer,ProviderData>,String,Transmission>{
    @Override
    public String create(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().create(obj);
    }

    @Override
    public ProviderData receive(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().read(obj);
    }

    @Override
    public String update(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().update(obj);
    }

    @Override
    public String delete(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().delete(obj);
    }

    @Override
    public ConcurrentHashMap<Integer,ProviderData> receiveAllElem() {
        return DAOFactory.getInstance().getProviderDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer,ProviderData> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getProviderDAO().receiveAllInLimit(obj);
    }
    public ConcurrentHashMap<Integer,ProviderData> findAllByLocationAndRating(ProviderData obj) {
        return DAOFactory.getInstance().getProviderDAO().findAllByLocationAndRating( obj);
    }
    public ConcurrentHashMap<Integer,ProviderData> findProviderByLocationRatingAndValue (ProviderData obj){
        return DAOFactory.getInstance().getProviderDAO().findProviderByLocationRatingAndValue(obj);
    }
}
