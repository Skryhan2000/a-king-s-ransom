package com.loneliness.server.service;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.dao.DAOFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class UserServiceImpl implements Service<UserData, ConcurrentHashMap<Integer, UserData>,String,Transmission>{
    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAllElem() {
        return DAOFactory.getInstance().getUserDAO().receiveAll();
    }

    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAllElemInLimit(Transmission obj) {
        return DAOFactory.getInstance().getUserDAO().receiveAllInLimit(obj);
    }

    public ConcurrentHashMap<Integer, UserData> findAllByLoginAndType(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().findAllByLoginAndType(obj);
    }


    public String authorization(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().receiveUserType(obj);
    }

    @Override
    public String create(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().create( obj);
    }

    @Override
    public UserData receive(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().read(obj);
    }

    @Override
    public String update(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().update(obj);
    }

    @Override
    public String delete(UserData obj) {
        return DAOFactory.getInstance().getUserDAO().delete(obj);
    }

    public String addManagerEmail(Map<Integer,String> map){
        return  DAOFactory.getInstance().getUserDAO().addManagerEmail(map);
    }
    public String updateManagerEmail(Map<Integer,String> map){
        return  DAOFactory.getInstance().getUserDAO().updateManagerEmail(map);
    }
    public String deleteManagerEmail(Map<Integer,String> map){
        return  DAOFactory.getInstance().getUserDAO().deleteManagerEmail(map);
    }
    public String readManagerEmail(Map<Integer,String> map){
        return  DAOFactory.getInstance().getUserDAO().readManagerEmail(map);
    }
}
