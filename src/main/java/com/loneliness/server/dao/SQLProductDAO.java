package com.loneliness.server.dao;

import com.loneliness.entity.Product;
import com.loneliness.entity.transmission.Transmission;

import java.sql.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SQLProductDAO implements CRUD<Product, ConcurrentHashMap<Integer, Product>,String> {

    private Product getDataFromResultSet(ResultSet resultSet) throws SQLException {
        Product product=new Product();
        product.setId(resultSet.getInt("product_ID"));
        product.setName(resultSet.getString("name"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setUnitPrice(resultSet.getInt("unit_price"));
        return product;
    }

    @Override
    public String create(Product product) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()){
        String sql = "INSERT products (name , quantity, unit_price ) " +
                "VALUES ('" +
                product.getName()+ "'," +
                product.getQuantity()+"," +
                product.getUnitPrice()+
                ");";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            sql="INSERT product_in_orders (product_ID, order_ID ) " +
                    "VALUES ( last_insert_id()," +
                    product.getOrderId()+
                    ");";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Успешное создание";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Такие данные уже существуют";
    }

    @Override
    public Product read(Product product) {
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM products WHERE ID = '" + product.getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if( resultSet.next()){
                return getDataFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public String update(Product product) {
        ResultSet resultSet;
        Statement statement;
        try (Connection connection = DataBaseConnection.getInstance().getConnection()) {

            statement = connection.createStatement();

            String sql = "SELECT * FROM product_in_orders WHERE product_ID= " + product.getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE `a-king-s-ransom`.products " +
                        "SET " +
                        "name='" + product.getName() + "'," +
                        "unit_price='" + product.getUnitPrice() + "'," +
                        "quantity='" + product.getQuantity() + "' " +
                        "WHERE ID =" + product.getId() + ";";
                if (statement.executeUpdate(sql) >= 1) {
                    return "Данные обновлен";
                } else return "ERROR Такие данные уже существует";
            } else {
                return "ERROR Нет таких данных";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка обновления";
    }

    @Override
    public String delete(Product product) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            String sql="DELETE FROM products WHERE ID = '"+ product.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) >= 1) {
                return "Данные удалены";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка удаления";
    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAll() {
        ConcurrentHashMap<Integer, Product> data=new ConcurrentHashMap<>();
        String sql;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_orders\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_orders.product_ID=`a-king-s-ransom`.products.ID" +
                    ";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Product product;
            while ( resultSet.next()){
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public ConcurrentHashMap<Integer, Product> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, Product> data = new ConcurrentHashMap<>();
        String sql;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {

            ResultSet resultSet;
            Statement statement;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_orders\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_orders.product_ID = `a-king-s-ransom`.products.ID \n" +
                    " LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Product product;
            while (resultSet.next()) {
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public ConcurrentHashMap<Integer, Product> receiveAllProductInSetOrderId(Set<Integer> ids){
        ConcurrentHashMap<Integer, Product> data = new ConcurrentHashMap<>();
        ResultSet resultSet;
        Statement statement;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            StringBuilder sql= new StringBuilder("SELECT * FROM product_in_orders join `a-king-s-ransom`.products\n" +
                    "on `a-king-s-ransom`.product_in_orders.product_ID=`a-king-s-ransom`.products.ID " +
                    "WHERE order_ID = ");
            for (Iterator<Integer> iterator = ids.iterator(); iterator.hasNext(); ) {
                Integer next =  iterator.next();
                sql.append(next);
                if(iterator.hasNext()){
                    sql.append(" or ");
                }

            }
                  sql.append(";");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());
            Product product;
            while (resultSet.next()) {
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
