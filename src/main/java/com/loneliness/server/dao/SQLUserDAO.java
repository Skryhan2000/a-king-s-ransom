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
        String sql = "INSERT Users (login , password , type, secret_answer, secret_question) "+
                "VALUES ('"+user.getLogin()+"','"+user.getPassword()+"','"+user.getType()+"','"+user.getSecretAnswer()+
                "','"+user.getSecretQuestion()+"');";
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
            String sql;
            if(user.getId()!=0) {
                sql = "SELECT * FROM Users WHERE id = '" + user.getId() + "';";
            }
            else {
                sql= "SELECT * FROM Users WHERE login = '" + user.getLogin() + "';";
            }
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData=new UserData();
            if( resultSet.next()){
                userData.setId(resultSet.getInt("id"));
                userData.setLogin(resultSet.getString("login"));
                userData.setType(resultSet.getString("type"));
                userData.setSecretAnswer(resultSet.getString("secret_answer"));
                userData.setSecretQuestion(resultSet.getString("secret_question"));

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

            String sql = "SELECT * FROM Users WHERE id = " + user.getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE users SET " +
                        "login='" + user.getLogin() + "'," +
                        "password='" + user.getPassword() + "'," +
                        "type='" + user.getType() + "'," +
                        "secret_answer='" + user.getSecretAnswer() + "'," +
                        "secret_question='" + user.getSecretQuestion() + "' " +
                        "WHERE ID=" + user.getId() + ";";
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
            String sql="DELETE FROM Users WHERE id = '"+user.getId()+"';";
            Statement statement = DataBaseConnection.getInstance().getConnection().createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String receiveUserType(UserData userData){
        ResultSet resultSet;
        Statement statement;
        try {
            String sql = "SELECT * FROM Users WHERE login = '" + userData.getLogin() + "' AND password ='"+
                    userData.getPassword()+"';";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            if( resultSet.next()){
                return resultSet.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "noType";
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
            userData.setLogin(resultSet.getString("login"));
            userData.setPassword(resultSet.getString("password"));
            userData.setType(resultSet.getString("type"));
            userData.setSecretAnswer(resultSet.getString("secret_answer"));
            userData.setSecretQuestion(resultSet.getString("secret_question"));
            data.put(userData.getId(),userData);
        }
        return data;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }
    public Map<Integer,UserData> findAllByLoginAndType(UserData userDataToFind){
        try {
            ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users ";
            boolean whereIsNotUsed=true;
            if(userDataToFind.getLogin()!=null){
                sql+="WHERE login = '" + userDataToFind.getLogin() + "' ";
                whereIsNotUsed=false;
            }else if(userDataToFind.getType()!=null){
                if(whereIsNotUsed) {
                    sql+="WHERE type = '" + userDataToFind.getType() + "' ";
                }
                else {
                    sql += "AND type = '" + userDataToFind.getType() + "' ";
                }

            }
            sql+=";";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
            while ( resultSet.next()){
                userData=new UserData();
                userData.setId(resultSet.getInt("id"));
                userData.setLogin(resultSet.getString("login"));
                userData.setPassword(resultSet.getString("password"));
                userData.setType(resultSet.getString("type"));
                userData.setSecretAnswer(resultSet.getString("secret_answer"));
                userData.setSecretQuestion(resultSet.getString("secret_question"));
                data.put(userData.getId(),userData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
