package com.loneliness.server.dao;


import com.loneliness.entity.ProductInStock;

import com.loneliness.entity.transmission.Transmission;


import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLProductInStockDAO implements CRUD{
    @Override
    public boolean create(Object productInStock) {
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
        String sql = "INSERT `a-king-s-ransom`.products (name , quantity , unit_price) "+
                "VALUES ('"+
                ((ProductInStock)productInStock).getName()+"','"+
                ((ProductInStock)productInStock).getQuantity()+"', "+
                ((ProductInStock)productInStock).getUnitPrice()+
                " ); ";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
             sql="INSERT `a-king-s-ransom`.product_in_stock (product_in_stock_ID ,provider_ID,receipt_date) "+
                "VALUES (last_insert_id(),"+
                ((ProductInStock)productInStock).getProvider_ID()+",'"+
                ((ProductInStock)productInStock).getReceipt_date().toString()+
                "');";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object read(Object productInStock) {
        try(Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_stock\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_stock.product_in_stock_ID=`a-king-s-ransom`.products.ID" +
                    " WHERE `a-king-s-ransom`.products.ID = '" + ((ProductInStock)productInStock).getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if( resultSet.next()){
                ((ProductInStock)productInStock).setId(resultSet.getInt("product_in_stock_ID"));
                ((ProductInStock)productInStock).setProvider_ID(resultSet.getInt("provider_ID"));
                ((ProductInStock)productInStock).setReceipt_date(resultSet.getDate("receipt_date").toLocalDate());
                ((ProductInStock)productInStock).setName(resultSet.getString("name"));
                ((ProductInStock)productInStock).setQuantity(resultSet.getInt("quantity"));
                ((ProductInStock)productInStock).setUnitPrice(resultSet.getInt("unit_price"));
                return productInStock;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productInStock;
    }

    @Override
    public boolean update(Object productInStock) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try(Connection connection= DataBaseConnection.getInstance().getConnection())  {

            statement = connection.createStatement();

            String sql = "SELECT * FROM product_in_stock WHERE product_in_stock_ID = " + ((ProductInStock) productInStock).getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE `a-king-s-ransom`.products , `a-king-s-ransom`.product_in_stock SET " +
                        "name='" + ((ProductInStock) productInStock).getName() + "'," +
                        "quantity='" + ((ProductInStock) productInStock).getQuantity() + "'," +
                        "unit_price='" + ((ProductInStock) productInStock).getUnitPrice() + "'," +
                        "provider_ID='" + ((ProductInStock) productInStock).getProvider_ID() + "'," +
                        "receipt_date='" + ((ProductInStock) productInStock).getReceipt_date() + "' " +
                        "WHERE ID=" + ((ProductInStock) productInStock).getId() + ";";
                return statement.executeUpdate(sql) > 0;

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
    public boolean delete(Object  productInStock) {
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            String sql="DELETE FROM product_in_stock WHERE product_in_stock_ID = '"+((ProductInStock)productInStock).getId()+"';";
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
    public Map<Integer,ProductInStock> receiveAll(){
        ConcurrentHashMap<Integer,ProductInStock> data=new ConcurrentHashMap<>();
        String sql;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_stock\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_stock.product_in_stock_ID=`a-king-s-ransom`.products.ID" +
                    ";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
           ProductInStock product;
            while ( resultSet.next()){
                product=new ProductInStock();
                product.setId(resultSet.getInt("product_in_stock_ID"));
                product.setProvider_ID(resultSet.getInt("provider_ID"));
                product.setReceipt_date(resultSet.getDate("receipt_date").toLocalDate());
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUnitPrice(resultSet.getInt("unit_price"));
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public Map<Integer,ProductInStock> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, ProductInStock> data = new ConcurrentHashMap<>();
        String sql;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {

            ResultSet resultSet;
            Statement statement;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_stock\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_stock.product_in_stock_ID = `a-king-s-ransom`.products.ID \n" +
                    " LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProductInStock product;
            while (resultSet.next()) {
                product=new ProductInStock();
                product.setId(resultSet.getInt("product_in_stock_ID"));
                product.setProvider_ID(resultSet.getInt("provider_ID"));
                product.setReceipt_date(resultSet.getDate("receipt_date").toLocalDate());
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUnitPrice(resultSet.getInt("unit_price"));
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Map<Integer,ProductInStock> findAllByNameAndQuantity(ProductInStock productInStockToFind){
        ConcurrentHashMap<Integer,ProductInStock> data=new ConcurrentHashMap<>();
        String sql;
        try (Connection connection= DataBaseConnection.getInstance().getConnection()) {
            ResultSet resultSet;
            Statement statement;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_stock\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_stock.product_in_stock_ID=`a-king-s-ransom`.products.ID ";
            boolean whereIsNotUsed=true;
            if(productInStockToFind.getName()!=null){
                sql+="WHERE name = '" + productInStockToFind.getName() + "' ";
                whereIsNotUsed=false;
            }
            if(productInStockToFind.getQuantity()!=0){
                if(whereIsNotUsed) {
                    sql+="WHERE quantity = '" + productInStockToFind.getQuantity() + "' ";
                }
                else {
                    sql += "AND quantity = '" + productInStockToFind.getQuantity() + "' ";
                }

            }
            sql+=";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ProductInStock product;
            while (resultSet.next()) {
                product=new ProductInStock();
                product.setId(resultSet.getInt("product_in_stock_ID"));
                product.setProvider_ID(resultSet.getInt("provider_ID"));
                product.setReceipt_date(resultSet.getDate("receipt_date").toLocalDate());
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setUnitPrice(resultSet.getInt("unit_price"));
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
