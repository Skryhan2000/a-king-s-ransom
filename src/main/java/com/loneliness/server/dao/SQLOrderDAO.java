package com.loneliness.server.dao;

import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.transmission.Transmission;

import java.math.BigDecimal;
import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

public class SQLOrderDAO implements CRUD<OrderData,ConcurrentHashMap<Integer,OrderData>,String>{

    private OrderData getDataFromResultSet(ResultSet resultSet) throws SQLException {
        OrderData order=new OrderData();
        order.setId(resultSet.getInt("ID"));
        order.setCustomerId(resultSet.getInt("customer_ID"));
        order.setOrderName(resultSet.getString("order_name"));
        order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
        order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
        order.setStatus(resultSet.getString("status"));
        order.setPayment(resultSet.getString("payment"));
        order.setManagerID(resultSet.getInt("manager_ID"));
        return order;
    }

    @Override
    public String create(OrderData  orderData) {
        String sql = "INSERT orders ( customer_ID, order_name , date_of_receiving, date_of_completion, status, payment ,manager_ID ) " +
                "VALUES ('" +
                orderData.getCustomerId() + "','" +
                orderData.getOrderName() + "','" +
                orderData.getDateOfReceiving() + "','" +
                orderData.getDateOfCompletion() + "','" +
                orderData.getStatus()+ "','" +
                orderData.getPayment().toString()+"','" +
                orderData.getManagerID()+
                "');";
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Успешное создание";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Такие данные уже существуют";
    }

    @Override
    public OrderData read(OrderData  orderData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM orders WHERE id = '" + orderData.getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getDataFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderData;
    }
    public BigDecimal calculateSumOfOrder(OrderData orderData){
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql="SELECT\n" +
                    "sum(unit_price*quantity) as price \n" +
                    "FROM `a-king-s-ransom`.product_in_orders\n" +
                    "join `a-king-s-ransom`.products\n" +
                    "on `a-king-s-ransom`.product_in_orders.product_ID=`a-king-s-ransom`.products.ID\n" +
                    "where `a-king-s-ransom`.product_in_orders.order_ID="+orderData.getId()+"\n" +
                    "GROUP BY order_ID;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getBigDecimal("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BigDecimal(0);
    }

    @Override
    public String update(OrderData orderData) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){

            statement = connection.createStatement();

            String sql  = "SELECT * FROM orders WHERE id = '" + orderData.getId() + "';";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE orders SET " +
                        "customer_ID ='" + orderData.getCustomerId() + "'," +
                        "order_name ='" + orderData.getOrderName() + "'," +
                        "date_of_receiving ='" + orderData.getDateOfReceiving() + "'," +
                        "date_of_completion ='" + orderData.getDateOfCompletion() + "' ," +
                        "status ='" + orderData.getStatus()+ "' ," +
                        "payment ='" + orderData.getPayment().toString()+"' ," +
                        "manager_ID = '"+ orderData.getManagerID()+"' "+
                        "WHERE ID = " + orderData.getId() + " ;";
                if (statement.executeUpdate(sql) == 1) {
                    return "Данные обновлен";
                } else return "ERROR Такие данные уже существует";
            } else {
                return "ERROR Нет таких данных";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка обновления";
    }

    @Override
    public String delete(OrderData orderData) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            String sql="DELETE FROM orders WHERE id = '"+ orderData.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) == 1) {
                return "Данные удалены";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка удаления";
    }

    @Override
    public ConcurrentHashMap<Integer,OrderData> receiveAll() {
        ConcurrentHashMap<Integer,OrderData> data=new ConcurrentHashMap<>();
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM orders ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderData order;
            while ( resultSet.next()){
                order=getDataFromResultSet(resultSet);
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }


    public ConcurrentHashMap<Integer,OrderCustomerData> receiveAllCustomerOrderInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, OrderCustomerData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT id,customer_ID,order_name,date_of_receiving,date_of_completion,\n" +
                    "\t\tstatus,payment,`a-king-s-ransom`.orders.manager_ID,`a-king-s-ransom`.manager_data.email \n" +
                    "FROM `a-king-s-ransom`.orders \n" +
                    "inner join `a-king-s-ransom`.manager_data \n" +
                    "on `a-king-s-ransom`.orders.manager_ID = `a-king-s-ransom`.manager_data.manager_id " +
                    "WHERE customer_id=" + transmission.getOrderCustomerData().getCustomerId()+
                    " LIMIT " +
                    transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderCustomerData order;
            while (resultSet.next()) {
                order=new OrderCustomerData();
                order.setId(resultSet.getInt("ID"));
                order.setCustomerId(resultSet.getInt("customer_ID"));
                order.setOrderName(resultSet.getString("order_name"));
                order.setDateOfReceiving(resultSet.getDate("date_of_receiving").toLocalDate());
                order.setDateOfCompletion(resultSet.getDate("date_of_completion").toLocalDate());
                order.setStatus(resultSet.getString("status"));
                order.setPayment(resultSet.getString("payment"));
                order.setManagerID(resultSet.getInt("manager_ID"));
                order.setManagerEmail(resultSet.getString("email"));
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ConcurrentHashMap<Integer,OrderData> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, OrderData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM orders LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderData order;
            while (resultSet.next()) {
                order=getDataFromResultSet(resultSet);
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
                order=getDataFromResultSet(resultSet);
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ConcurrentHashMap<Integer,OrderData> searchForBurningOrders(OrderData orderData) {
        ConcurrentHashMap<Integer, OrderData> data = new ConcurrentHashMap<>();
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
           String sql="SELECT * FROM `a-king-s-ransom`.orders\n" +
                    "where date_of_completion between '"+orderData.getDateOfReceiving()+"' and '"+orderData.getDateOfCompletion()+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            OrderData order;
            while (resultSet.next()) {
                order=getDataFromResultSet(resultSet);
                data.put(order.getId(),order);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
