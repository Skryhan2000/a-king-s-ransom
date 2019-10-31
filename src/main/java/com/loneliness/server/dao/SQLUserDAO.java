package com.loneliness.server.dao;



import com.loneliness.entity.user.UserData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLUserDAO implements CRUD{
    @Override
    public boolean create(UserData user) {
        String sql = "INSERT Users (login , password , type ) "+
                "VALUES ('"+user.getLogin()+"','"+user.getPassword()+"','"+user.getType()+"');";
        try {
            PreparedStatement preparedStatement = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserData read(UserData user) {
        try {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users WHERE username = '" + user.getLogin() + "' AND password ='"+
                    user.getPassword()+"';";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData=new UserData();
            if( resultSet.next()){
                userData.setId(resultSet.getInt("id"));
                userData.setLogin(resultSet.getString("userlogin"));
                userData.setType(resultSet.getString("type"));
                return userData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(UserData user) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {

            statement = DataBaseConnection.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM User WHERE ID = '" + user.getId() + "';";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                sql = "UPDATE users SET " +
                        "username='" + user.getLogin() + "'," +
                        "password='" + user.getPassword() + "'," +
                        "type='" + user.getType() + "'," +
                        "WHERE ID='" + user.getId() + "';";
                try {
                    return statement.executeUpdate(sql) == 1;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    @Override
    public boolean delete(UserData user) {
        try {
            String sql="DELETE FROM Users WHERE login = '"+user.getLogin()+"';";
            ResultSet resultSet=null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean isUserExist(UserData userData){
        ResultSet resultSet;
        Statement statement;
        try {
            String sql = "SELECT * FROM Users WHERE username = '" + userData.getLogin() + "' AND password ='"+
                    userData.getPassword()+"';";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Map<Integer,UserData> receiveAll(){
        try {
            ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users;";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
        while ( resultSet.next()){
            userData=new UserData();
            userData.setId(resultSet.getInt("id"));
            userData.setLogin(resultSet.getString("userlogin"));
            userData.setType(resultSet.getString("type"));
            data.put(userData.getId(),userData);
        }
        return data;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }
}
