package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Iterator;
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
            sql = " INSERT Users (login , password , type, secret_answer, secret_question) " +
                    "VALUES ('" +
                    user.getLogin() + "','" +
                    user.getPassword() + "','" +
                    user.getType().toString() + "','" +
                    user.getSecretAnswer() + "','" +
                    user.getSecretQuestion() + "'); " +
                    "INSERT INTO customer_representative (customer_id,user_id) VALUES(" +
                    user.getType().getID() +
                    ",LAST_INSERT_ID()); ";
        } else {
            sql = "INSERT Users (login , password , type, secret_answer, secret_question) " +
                    "VALUES ('" +
                    user.getLogin() + "','" +
                    user.getPassword() + "','" +
                    user.getType().toString() + "','" +
                    user.getSecretAnswer() + "','" +
                    user.getSecretQuestion() + "');";
        }

        try ( Connection connection= DataBaseConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Пользователь успешно создан";
        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR Ошибка создания пользователя. Такой пользователь уже существует";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка подключения к данным";
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
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);

        }
        return userData;
    }

    @Override
    public String update(UserData user) {
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
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
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка обновления";
        }
    }

    @Override
    public String delete(UserData user) {
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql="DELETE FROM Users WHERE id = '"+user.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return "Пользователь удален";
            }
            else {
                return "ERROR Нет такого пользователя";
            }

        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR Ошибка удаления";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка подключения к данным";
        }

    }

    public String receiveUserType(UserData userData){
        ResultSet resultSet;
        Statement statement;
        try( Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql = "SELECT * FROM Users WHERE login = '" + userData.getLogin() + "' AND password ='"+
                    userData.getPassword()+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if( resultSet.next()){
                UserData.Type type=UserData.Type.valueOf(resultSet.getString("type"));
                type.setID(resultSet.getInt("id"));
                return type.toString()+" "+type.getID();
            }
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
            return UserData.Type.valueOf("NO_TYPE").getDeclaringClass().getName();
        }
        return UserData.Type.valueOf("NO_TYPE").getDeclaringClass().getName();
    }

    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAll(){
        ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()) {

            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM users ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            UserData userData;
        while ( resultSet.next()){
            userData=getDataFromResultSet(resultSet);
            data.put(userData.getId(),userData);
        }
        return data;
    } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
    }
        return data;
    }
    @Override
    public ConcurrentHashMap<Integer, UserData> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, UserData> data = new ConcurrentHashMap<>();
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
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
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
        }
        return data;
    }
    public ConcurrentHashMap<Integer, UserData> findAllByLoginAndType(UserData userDataToFind){
        ConcurrentHashMap<Integer,UserData> data=new ConcurrentHashMap<>();
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()) {

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
        } catch (SQLException | PropertyVetoException e) {
            logger.catching(e);
        }
        return data;
    }
    public String addManagerEmail(Map<Integer,String> map){
        StringBuilder sql=new StringBuilder("INSERT manager_data (manager_id,email) VALUES ");
        for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            Integer next =  iterator.next();
            sql.append("( ").append(next).append(" ,'").append(map.get(next)).append("')");
            if(iterator.hasNext()){
                sql.append(" , ");
            }

        }
        sql.append(" ;");
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
            return "Успешное создание";
        } catch (SQLException e) {
            return "ERROR Такие данные уже существуют";
        } catch (PropertyVetoException e) {
            return "ERROR Ошибка подключения к данным";
        }
    }
    public String updateManagerEmail(Map<Integer,String>map){
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        int id=map.keySet().iterator().next();

        try( Connection connection= DataBaseConnection.getInstance().getConnection()) {
                String sql = "UPDATE manager_data SET " +
                        "manager_id ='" +id + "'," +
                        "email ='" + map.get(id) + " " +
                        "WHERE manager_id = " + id + " ;";
                statement = connection.createStatement();
                if (statement.executeUpdate(sql) == 1) {
                    return "Данные обновлен";
                } else return "ERROR Такие данные уже существует";
        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR Ошибка обновления";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка подключения к данным";
        }
    }
    public String deleteManagerEmail(Map<Integer,String>map){
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql="DELETE FROM manager_data WHERE manager_id = '"+ map.keySet().iterator().next()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return "Данные удалены";
            }

        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR Ошибка удаления";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка подключения к данным";
        }
        return "ERROR Ошибка удаления";
    }
    public String readManagerEmail(Map<Integer,String>map){
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){

            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM manager_data WHERE manager_id = '" + map.keySet().iterator().next() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            logger.catching(e);
            return "ERROR Ошибка чтения данных";
        } catch (PropertyVetoException e) {
            logger.catching(e);
            return "ERROR Ошибка подключения к данным";
        }
        return " ";
    }
}
