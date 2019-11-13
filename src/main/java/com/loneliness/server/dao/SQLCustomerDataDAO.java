package com.loneliness.server.dao;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

public class SQLCustomerDataDAO implements CRUD<CustomerData>{

    private CustomerData getDataFromResultSet(ResultSet resultSet) throws SQLException {
        CustomerData customerData=new CustomerData();
        customerData.setId(resultSet.getInt("ID"));
        customerData.setName(resultSet.getString("name"));
        customerData.setNumberOfOrders(resultSet.getInt("number_of_orders"));
        customerData.setLocation(resultSet.getString("location"));
        customerData.setEmail(resultSet.getString("email"));
        return  customerData;
    }

    @Override
    public boolean create(CustomerData  customerData) {
        String sql = "INSERT customers ( name, number_of_orders , location, email ) " +
                "VALUES ('" +
                customerData.getName() + "','" +
                customerData.getNumberOfOrders() + "','" +
                customerData.getLocation() + "','" +
                customerData.getEmail() +
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
    public Object read(CustomerData customerData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM customers  WHERE id = '" + customerData.getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(CustomerData customerData) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){

            statement = connection.createStatement();

            String sql  = "SELECT * FROM customers WHERE id = '" + customerData.getId() + "';";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE customers SET " +
                        "name ='" + customerData.getName() + "'," +
                        "number_of_orders ='" + customerData.getNumberOfOrders() + "'," +
                        "location ='" + customerData.getLocation() + "' ," +
                        "email ='" + customerData.getEmail()+ "' " +
                        "WHERE id = " + customerData.getId() + " ;";
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
    public boolean delete(CustomerData customerData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql="DELETE FROM customers WHERE id = '"+ customerData.getId()+"';";
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
        ConcurrentHashMap<Integer,CustomerData> data=new ConcurrentHashMap<>();
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM customers ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            CustomerData customerData;
            while ( resultSet.next()){
                customerData=getDataFromResultSet(resultSet);
                data.put(customerData.getId(),customerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Object receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, CustomerData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM customers LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            CustomerData customerData;
            while (resultSet.next()) {
                customerData=getDataFromResultSet(resultSet);
                data.put(customerData.getId(),customerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public  ConcurrentHashMap<Integer,CustomerData> findAllByNameAndNumberOfOrders(CustomerData customerDataToFind){
        ConcurrentHashMap<Integer,CustomerData> data=new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM customers ";
            boolean whereIsNotUsed=true;
            if(customerDataToFind.getName()!=null){
                sql+="WHERE name = '" + customerDataToFind.getName()+ "' ";
                whereIsNotUsed=false;
            }
            if(customerDataToFind.getNumberOfOrders()!=0){
                if(whereIsNotUsed) {
                    sql+="WHERE number_of_orders = " + customerDataToFind.getNumberOfOrders()+ " ";
                }
                else {
                    sql += "AND number_of_orders = " +customerDataToFind.getNumberOfOrders() + " ";
                }

            }
            sql+=";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            CustomerData nCustomerData;
            while (resultSet.next()) {
                nCustomerData=new CustomerData();
                nCustomerData.setId(resultSet.getInt("ID"));
                nCustomerData.setName(resultSet.getString("name"));
                nCustomerData.setNumberOfOrders(resultSet.getInt("number_of_orders"));
                nCustomerData.setLocation(resultSet.getString("location"));
                nCustomerData.setEmail(resultSet.getString("email"));
                data.put(nCustomerData.getId(),nCustomerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
