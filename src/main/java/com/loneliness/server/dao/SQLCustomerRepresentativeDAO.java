package com.loneliness.server.dao;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.CustomerRepresentative;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

public class SQLCustomerRepresentativeDAO implements CRUD <CustomerRepresentative, ConcurrentHashMap<Integer,CustomerRepresentative>,String>{

    private CustomerRepresentative getDataFromResultSet(ResultSet resultSet) throws SQLException {
        CustomerRepresentative customerRepresentative=new CustomerRepresentative();
        customerRepresentative.setCustomerRepresentativeID(resultSet.getInt("customer_representative_ID"));
        customerRepresentative.setCustomerID(resultSet.getInt("customer_ID"));
        customerRepresentative.setUserID(resultSet.getInt("user_id"));
        return customerRepresentative;
    }

    @Override
    public String create(CustomerRepresentative obj) {
        String sql = "INSERT customer_representative ( customer_id, user_id ) " +
                "VALUES (" +
                obj.getCustomerID() + " , " +
                obj.getUserID() +
                " );";
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Успешное создание";
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Такие данные уже существуют";
    }

    @Override
    public CustomerRepresentative read(CustomerRepresentative obj) {
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM customer_representative  WHERE customer_representative_ID = '" + obj.getCustomerRepresentativeID() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getDataFromResultSet(resultSet);
            }
            else  obj.setCustomerRepresentativeID(-1);
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
            obj.setCustomerRepresentativeID(-1);
        }

        return obj;
    }

    @Override
    public String update(CustomerRepresentative obj) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();

            statement = connection.createStatement();

            String sql  = "SELECT * FROM customer_representative WHERE customer_representative_ID =  " + obj.getCustomerRepresentativeID() + " ;";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE customer_representative SET " +
                        "customer_id = " +obj.getCustomerID() + " ," +
                        "user_id =" + obj.getUserID() + " " +

                        "WHERE customer_representative_ID = " + obj.getCustomerRepresentativeID()  + " ;";
                if (statement.executeUpdate(sql) == 1) {
                    return "Данные обновлен";
                } else return "ERROR Такие данные уже существует";
            } else {
                return "ERROR Нет таких данных";
            }
        }
        catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка обновления";
    }

    @Override
    public String delete(CustomerRepresentative obj) {
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            String sql="DELETE FROM customer_representative WHERE customer_representative_ID = '"+ obj.getCustomerRepresentativeID()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return "Данные удалены";
            }

        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка удаления";
    }

    @Override
    public ConcurrentHashMap<Integer, CustomerRepresentative> receiveAll() {
        ConcurrentHashMap<Integer,CustomerRepresentative> data=new ConcurrentHashMap<>();
        try{
            Connection connection= DataBaseConnection.getInstance().getConnection();
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM customer_representative ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            CustomerRepresentative representative;
            while ( resultSet.next()){
                representative=getDataFromResultSet(resultSet);
                data.put(representative.getCustomerRepresentativeID(),representative);
            }
            return data;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public ConcurrentHashMap<Integer, CustomerRepresentative> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, CustomerRepresentative> data = new ConcurrentHashMap<>();
        try {
            Connection connection= DataBaseConnection.getInstance().getConnection();
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM customer_representative LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            CustomerRepresentative representative;
            while ( resultSet.next()){
                representative=getDataFromResultSet(resultSet);
                data.put(representative.getCustomerRepresentativeID(),representative);
            }
            return data;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }
}
