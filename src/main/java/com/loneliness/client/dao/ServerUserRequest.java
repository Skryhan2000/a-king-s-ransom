package com.loneliness.client.dao;

import com.loneliness.entity.user.UserData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.client.launcher.Client;


import java.io.IOException;
import java.util.Map;

public class ServerUserRequest implements CRUD{
    @Override
    public boolean create(UserData user) {
        try {
            Transmission transmission = new Transmission();
            transmission.setCommand("CREATE_USER");
            transmission.setUserData(user);
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
//        String sql = "INSERT Users (login , password , type ) "+
//                "VALUES ('"+com.loneless.entity.user.getLogin()+"','"+com.loneless.entity.user.getPassword()+"','"+com.loneless.entity.user.getType()+"');";
//        try {
//            PreparedStatement preparedStatement = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;


    @Override
    public UserData read(UserData user) {
//        try {
//            ResultSet resultSet;
//            Statement statement;
//            String sql = "SELECT * FROM Users WHERE username = '" + com.loneless.entity.user.getLogin() + "' AND password ='"+
//                    com.loneless.entity.user.getPassword()+"';";
//            statement = DataBaseConnection.getInstance().getConnection().createStatement();
//            resultSet = statement.executeQuery(sql);
//            UserData userData=new UserData();
//            if( resultSet.next()){
//                userData.setId(resultSet.getInt("id"));
//                userData.setLogin(resultSet.getString("userlogin"));
//                userData.setType(resultSet.getString("type"));
//                return userData;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public boolean update(UserData user) {
//        ResultSet resultSet=null;
//        Statement statement = null;
//        PreparedStatement preparedStatement = null;
//        try {
//
//            statement = DataBaseConnection.getInstance().getConnection().createStatement();
//
//            String sql = "SELECT * FROM User WHERE ID = '" + com.loneless.entity.user.getId() + "';";
//            resultSet = statement.executeQuery(sql);
//            if (!resultSet.next()) {
//                sql = "UPDATE users SET " +
//                        "username='" + com.loneless.entity.user.getLogin() + "'," +
//                        "password='" + com.loneless.entity.user.getPassword() + "'," +
//                        "type='" + com.loneless.entity.user.getType() + "'," +
//                        "WHERE ID='" + com.loneless.entity.user.getId() + "';";
//                try {
//                    return statement.executeUpdate(sql) == 1;
//
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//            } else {
//                return false;
//            }
//        }
//        catch (SQLException e) {
//                e.printStackTrace();
//            }
        return false;
    }

    @Override
    public boolean delete(UserData user) {
//        try {
//            String sql="DELETE FROM Users WHERE login = '"+com.loneless.entity.user.getLogin()+"';";
//            ResultSet resultSet=null;
//            Statement statement = null;
//            PreparedStatement preparedStatement = null;
//            statement = DataBaseConnection.getInstance().getConnection().createStatement();
//            if(statement.executeUpdate(sql) == 1) {
//                return true;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        return false;
    }

    public boolean authorize(UserData userData){
        Transmission transmission = new Transmission();
        transmission.setCommand("AUTHORIZATION_USER");
        transmission.setUserData(userData);
        try {
            Client.getOutObject().writeObject(transmission);
            return (Boolean) Client.getInObject().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        ResultSet resultSet;
//        Statement statement;
//        try {
//            String sql = "SELECT * FROM Users WHERE username = '" + userData.getLogin() + "' AND password ='"+
//                    userData.getPassword()+"';";
//            statement = DataBaseConnection.getInstance().getConnection().createStatement();
//            resultSet = statement.executeQuery(sql);
//            return resultSet.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return false;
    }
    @Override
    public Map<Integer,UserData> receiveAll(){
//        try {
//            ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
//            ResultSet resultSet;
//            Statement statement;
//            String sql = "SELECT * FROM Users;";
//            statement = DataBaseConnection.getInstance().getConnection().createStatement();
//            resultSet = statement.executeQuery(sql);
//            UserData userData;
//        while ( resultSet.next()){
//            userData=new UserData();
//            userData.setId(resultSet.getInt("id"));
//            userData.setLogin(resultSet.getString("userlogin"));
//            userData.setType(resultSet.getString("type"));
//            data.put(userData.getId(),userData);
//        }
//        return data;
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
        return null;
    }
}
