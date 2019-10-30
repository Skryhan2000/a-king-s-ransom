package com.loneless.server.logic;

import com.loneless.server.dao.DAOException;
import com.loneless.server.dao.DAOFactory;
import com.loneless.server.dao.DataBaseConnection;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.entity.user.UserPrivateData;


public class Logic {
private static final Logic instance=new Logic();

    public static Logic getInstance() {
        return instance;
    }

    public boolean isUserExist(UserData userData){
        return DAOFactory.getInstance().getUserDAO().isUserExist(userData);
    }

    public UserData receiveUserData(UserData userData){
        return DAOFactory.getInstance().getUserDAO().read(userData);
    }

    public boolean updateUserData(UserData userData){
        return DAOFactory.getInstance().getUserDAO().update(userData);
    }

    public boolean createUser(UserData userData){
        return DAOFactory.getInstance().getUserDAO().create(userData);
    }

    public boolean deleteUser(UserData userData){
        return DAOFactory.getInstance().getUserDAO().delete(userData);
    }
}
