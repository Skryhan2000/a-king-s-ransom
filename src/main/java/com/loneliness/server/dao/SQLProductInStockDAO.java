package com.loneliness.server.dao;


import com.loneliness.entity.ProductInStock;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;


import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLProductInStockDAO implements CRUD<ProductInStock,ConcurrentHashMap<Integer, ProductInStock>,String>{

    private ProductInStock getDataFromResultSet(ResultSet resultSet) throws SQLException {
        ProductInStock productInStock =new ProductInStock();
        productInStock.setId(resultSet.getInt("product_in_stock_ID"));
        productInStock.setProvider_ID(resultSet.getInt("provider_ID"));
        productInStock.setReceipt_date(resultSet.getDate("receipt_date").toLocalDate());
        productInStock.setName(resultSet.getString("name"));
        productInStock.setQuantity(resultSet.getInt("quantity"));
        productInStock.setUnitPrice(resultSet.getInt("unit_price"));
        return productInStock;
    }

    @Override
    public String create(ProductInStock productInStock) {
        try( Connection connection= DataBaseConnection.getInstance().getConnection()) {
        String sql = "INSERT products (name , quantity , unit_price) \n"+
                "VALUES ('"+
                productInStock.getName()+"', "+
                productInStock.getQuantity()+" , "+
                productInStock.getUnitPrice()+
                " );\n ";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
             sql=" INSERT product_in_stock (product_in_stock_ID ,provider_ID,receipt_date)\n "+
                "VALUES (last_insert_id(),"+
                productInStock.getProvider_ID()+",'"+
                productInStock.getReceipt_date().toString()+
                "');";
            preparedStatement =connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "Успешное создание";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Такие данные уже существуют";
    }

    @Override
    public ProductInStock read(ProductInStock productInStock) {
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM `a-king-s-ransom`.product_in_stock\n" +
                    "JOIN `a-king-s-ransom`.products \n" +
                    "ON `a-king-s-ransom`.product_in_stock.product_in_stock_ID=`a-king-s-ransom`.products.ID" +
                    " WHERE `a-king-s-ransom`.products.ID = '" + productInStock.getId() + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            if( resultSet.next()){
                return getDataFromResultSet(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return productInStock;
    }

    @Override
    public String update(ProductInStock productInStock) {
        ResultSet resultSet;
        Statement statement;
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()) {

            statement = connection.createStatement();

            String sql = "SELECT * FROM product_in_stock WHERE product_in_stock_ID = " + productInStock.getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE `a-king-s-ransom`.products , `a-king-s-ransom`.product_in_stock SET " +
                        "name='" + productInStock.getName() + "'," +
                        "quantity='" + productInStock.getQuantity() + "'," +
                        "unit_price='" + productInStock.getUnitPrice() + "'," +
                        "provider_ID='" + productInStock.getProvider_ID() + "'," +
                        "receipt_date='" + productInStock.getReceipt_date() + "' " +
                        "WHERE ID=" + productInStock.getId() + ";";
                if (statement.executeUpdate(sql) >= 1) {
                    return "Данные обновлен";
                } else return "ERROR Такие данные уже существует";
            } else {
                return "ERROR Нет таких данных";
            }
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка обновления";
    }

    @Override
    public String delete(ProductInStock productInStock) {
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()) {
            String sql="DELETE FROM product_in_stock WHERE product_in_stock_ID = '"+ productInStock.getId()+"';";
            Statement statement = connection.createStatement();
            if(statement.executeUpdate(sql) >= 1) {
                return "Данные удалены";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return "ERROR Ошибка удаления";
    }

    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAll(){
        ConcurrentHashMap<Integer,ProductInStock> data=new ConcurrentHashMap<>();
        String sql;
        try ( Connection connection= DataBaseConnection.getInstance().getConnection()){
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
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }
    @Override
    public ConcurrentHashMap<Integer, ProductInStock> receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, ProductInStock> data = new ConcurrentHashMap<>();
        String sql;
        try( Connection connection= DataBaseConnection.getInstance().getConnection()) {

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
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ConcurrentHashMap<Integer, ProductInStock> findAllByNameAndQuantity(ProductInStock productInStockToFind){
        ConcurrentHashMap<Integer,ProductInStock> data=new ConcurrentHashMap<>();
        String sql;
        try  ( Connection connection= DataBaseConnection.getInstance().getConnection()){
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
                product=getDataFromResultSet(resultSet);
                data.put( product.getId(), product);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return data;
    }
}
