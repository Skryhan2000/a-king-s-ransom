package com.loneliness.server.dao;

import com.loneliness.entity.OrderData;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLOrderDAO implements CRUD{
    @Override
    public boolean create(Object  orderData) {
        String sql = "INSERT orders ( customer_ID, order_name , date_of_receiving, date_of_completion, status, payment ) " +
                "VALUES ('" +
                ((OrderData) orderData).getCustomerId() + "','" +
                ((OrderData) orderData).getOrderName() + "','" +
                ((OrderData) orderData).getDateOfReceiving() + "','" +
                ((OrderData) orderData).getDateOfCompletion() + "','" +
                ((OrderData) orderData).getStatus()+ "','" +
                ((OrderData) orderData).getPayment().toString()+
                "');";
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object read(Object  orderData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql;
            OrderData order=new OrderData();
            sql = "SELECT * FROM orders WHERE id = '" + ((OrderData) orderData).getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("customer_ID"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
                order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
                order.setStatus(resultSet.getString("status"));
                order.setPayment(resultSet.getString("payment"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Object orderData) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){

            statement = connection.createStatement();

            String sql  = "SELECT * FROM orders WHERE id = '" + ((OrderData) orderData).getId() + "';";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE orders SET " +
                        "customer_ID ='" + ((OrderData) orderData).getCustomerId() + "'," +
                        "order_name ='" + ((OrderData) orderData).getOrderName() + "'," +
                        "date_of_receiving ='" +((OrderData) orderData).getDateOfReceiving() + "'," +
                        "date_of_completion ='" +((OrderData) orderData).getDateOfCompletion() + "' ," +
                        "status ='" +((OrderData) orderData).getStatus()+ "' ," +
                        "payment ='" +((OrderData) orderData).getPayment().toString()+"' " +
                        "WHERE ID = " + ((OrderData) orderData).getId() + " ;";
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
    public boolean delete(Object orderData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql="DELETE FROM orders WHERE id = '"+((OrderData) orderData).getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public Object receiveAll() {
        ConcurrentHashMap<Integer,OrderData> data=new ConcurrentHashMap<>();
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM orders ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderData order;
            while ( resultSet.next()){
                order=new OrderData();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("customer_ID"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
                order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
                order.setStatus(resultSet.getString("status"));
                order.setPayment(resultSet.getString("payment"));
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Object receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, OrderData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM orders LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderData order;
            while (resultSet.next()) {
                order=new OrderData();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("customer_ID"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
                order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
                order.setStatus(resultSet.getString("status"));
                order.setPayment(resultSet.getString("payment"));
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public  ConcurrentHashMap<Integer,OrderData> findAllByDateOfCompletionAndStatus(OrderData orderDataToFind){
        ConcurrentHashMap<Integer,OrderData> data=new ConcurrentHashMap<>();
        OrderData order;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM orders ";
            boolean whereIsNotUsed=true;
            if(orderDataToFind.getDateOfCompletion()!=null){
                sql+="WHERE date_of_completion = '" + orderDataToFind.getDateOfCompletion() + "' ";
                whereIsNotUsed=false;
            }
            if(orderDataToFind.getStatus()!=null){
                if(whereIsNotUsed) {
                    sql+="WHERE status = " + orderDataToFind.getStatus() + " ";
                }
                else {
                    sql += "AND status = " +orderDataToFind.getStatus() + " ";
                }

            }
            sql+=";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while ( resultSet.next()){
                order=new OrderData();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("customer_ID"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
                order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
                order.setStatus(resultSet.getString("status"));
                order.setPayment(resultSet.getString("payment"));
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
