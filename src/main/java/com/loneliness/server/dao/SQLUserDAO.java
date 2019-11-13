package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLUserDAO implements CRUD<UserData,ConcurrentHashMap<Integer, UserData>,String>{

    private UserData getDataFromResultSet(ResultSet resultSet) throws SQLException {
            UserData userData = new UserData();
            userData.setId(resultSet.getInt("id"));
            userData.setLogin(resultSet.getString("login"));
            userData.setPassword(resultSet.getString("password"));
            userData.setType(resultSet.getString("type"));
            userData.setSecretAnswer(resultSet.getString("secret_answer"));
            userData.setSecretQuestion(resultSet.getString("secret_question"));
            return userData;
        }

    @Override
    public String create(UserData user) {
        String sql;
        if (user.getType() == UserData.Type.CLIENT) {
            sql = "BEGIN; INSERT Users (login , password , type, secret_answer, secret_question) " +
                    "VALUES ('" +
                    user.getLogin() + "','" +
                    user.getPassword() + "','" +
                    user.getType().toString() + "','" +
                    user.getSecretAnswer() + "','" +
                    user.getSecretQuestion() + "'); " +
                    "INSERT INTO customer_representative (customer_id,user_id) VALUES(" +
                    user.getType().getCompanyID() +
                    ",LAST_INSERT_ID()); COMMIT;";
        } else {
            sql = "INSERT Users (login , password , type, secret_answer, secret_question) " +
                    "VALUES ('" +
                    user.getLogin() + "','" +
                    user.getPassword() + "','" +
                    user.getType().toString() + "','" +
                    user.getSecretAnswer() + "','" +
                    user.getSecretQuestion() + "');";
        }

        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Пользователь успешно создан";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR Ошибка создания пользователя. Такой пользователь уже существует";
        }

    }

    @Override
    public UserData read(UserData user) {
        UserData userData=new UserData();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql;
            if(user.getId()!=0) {
                sql = "SELECT * FROM Users WHERE id = '" + user.getId() + "';";
            }
            else {
                sql= "SELECT * FROM Users WHERE login = '" + user.getLogin() + "';";
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if( resultSet.next()){
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userData;
    }

    @Override
    public String update(UserData user) {
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {

            statement = connection.createStatement();

            String sql = "SELECT * FROM Users WHERE id = " + user.getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE users SET " +
                        "login='" + user.getLogin() + "'," +
                        "password='" + user.getPassword() + "'," +
                        "type='" + user.getType().toString() + "'," +
                        "secret_answer='" + user.getSecretAnswer() + "'," +
                        "secret_question='" + user.getSecretQuestion() + "' " +
                        "WHERE ID=" + user.getId() + ";";

                if (statement.executeUpdate(sql) == 1) {
                    return "Пользователь обновлен";
                } else return "ERROR Такой пользователь уже существует";

            } else {
                return "ERROR Нет такого пользователя";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR Ошибка обновления";
        }
    }

    @Override
    public String delete(UserData user) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            String sql="DELETE FROM Users WHERE id = '"+user.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return "Пользователь удален";
            }
            else {
                return "ERROR Нет такого пользователя";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR Ошибка удаления";
        }

    }

    public UserData.Type receiveUserType(UserData userData){
        ResultSet resultSet;
        Statement statement;
        try(Connection connection= DataBaseConnection.getInstance().getConnection())  {
            String sql = "SELECT * FROM Users WHERE login = '" + userData.getLogin() + "' AND password ='"+
                    userData.getPassword()+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if( resultSet.next()){
                return UserData.Type.valueOf(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserData.Type.valueOf("NO_TYPE");
    }

    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAll(){
        ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
        while ( resultSet.next()){
            userData=getDataFromResultSet(resultSet);
            data.put(userData.getId(),userData);
        }
        return data;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return data;
    }
    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, UserData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {

            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
            while (resultSet.next()) {
                userData=getDataFromResultSet(resultSet);
                data.put(userData.getId(),userData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ConcurrentHashMap<Integer, UserData> findAllByLoginAndType(UserData userDataToFind){
        ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM Users ";
            boolean whereIsNotUsed=true;
            if(userDataToFind.getLogin()!=null){
                sql+="WHERE login = '" + userDataToFind.getLogin() + "' ";
                whereIsNotUsed=false;
            }
            if(userDataToFind.getType()!=null){
                if(whereIsNotUsed) {
                    sql+="WHERE type = '" + userDataToFind.getType() + "' ";
                }
                else {
                    sql += "AND type = '" + userDataToFind.getType() + "' ";
                }

            }
            sql+=";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
            while ( resultSet.next()){
                userData=getDataFromResultSet(resultSet);
                data.put(userData.getId(),userData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
