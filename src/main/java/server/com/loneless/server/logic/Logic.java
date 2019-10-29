package com.loneless.server.logic;

import com.loneless.server.dao.DAOException;
import com.loneless.server.dao.DataBaseConnection;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.entity.user.UserPrivateData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Logic {
private static final Logic instance=new Logic();

    public static Logic getInstance() {
        return instance;
    }

    public boolean isUserExist(UserPrivateData userPrivateData){
        ResultSet resultSet;
        Statement statement;
        try {
            String sql = "SELECT * FROM Users WHERE username = '" + userPrivateData.getLogin() + "' AND password ='"+
                userPrivateData.getPassword()+"';";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
