package com.loneliness.server.dao;

import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SQLProviderDAO implements CRUD {
    @Override
    public boolean create(Object provider) {
        String sql = "INSERT providers (ID , name , rating, location, email ) " +
                "VALUES ('" + ((ProviderData) provider).getId() + "','" + ((ProviderData) provider).getName() + "','" +
                (((ProviderData) provider).getRating() + "','" + ((ProviderData) provider).getLocation()+ "','" +
                        ((ProviderData) provider).getEmail() + "');");
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
    public Object read(Object provider) {
        try {
            ResultSet resultSet;
            Statement statement;
            String sql;
            sql = "SELECT * FROM providers WHERE id = '" + ((ProviderData) provider).getId() + "';";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData = new ProviderData();
            if (resultSet.next()) {
                providerData.setId(resultSet.getInt("ID"));
                providerData.setName(resultSet.getString("name"));
                providerData.setRating(resultSet.getInt("rating"));
                providerData.setLocation(resultSet.getString("location"));
                providerData.setEmail(resultSet.getString("email"));
                return providerData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Object provider) {
        ResultSet resultSet=null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {

            statement = DataBaseConnection.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM providers WHERE id = " + ((ProviderData)provider).getId() + ";";
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "UPDATE providers SET " +
                        "name='" + ((ProviderData)provider).getName() + "'," +
                        "rating='" + ((ProviderData)provider).getRating() + "'," +
                        "location='" +((ProviderData)provider).getLocation() + "'," +
                        "email='" +((ProviderData)provider).getEmail() + "' " +
                        "WHERE ID = " + ((ProviderData)provider).getId() + " ;";
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
    public boolean delete(Object provider) {
        try {
            String sql="DELETE FROM providers WHERE id = '"+((ProviderData)provider).getId()+"';";
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

    @Override
    public Object receiveAll() {
        ConcurrentHashMap<Integer,ProviderData> data=new ConcurrentHashMap<>();
        try {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers ;";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while ( resultSet.next()){
                providerData=new ProviderData();
                providerData.setId(resultSet.getInt("ID"));
                providerData.setName(resultSet.getString("name"));
                providerData.setRating(resultSet.getInt("rating"));
                providerData.setLocation(resultSet.getString("location"));
                providerData.setEmail(resultSet.getString("email"));
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Object receiveAllInLimit(Transmission transmission) {
        ConcurrentHashMap<Integer, ProviderData> data = new ConcurrentHashMap<>();
        try {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers LIMIT " + transmission.getFirstIndex() + ", " + transmission.getLastIndex() + " ;";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while (resultSet.next()) {
                providerData=new ProviderData();
                providerData.setId(resultSet.getInt("ID"));
                providerData.setName(resultSet.getString("name"));
                providerData.setRating(resultSet.getInt("rating"));
                providerData.setLocation(resultSet.getString("location"));
                providerData.setEmail(resultSet.getString("email"));
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Map<Integer,ProviderData> findAllByLocationAndRating(ProviderData providerDataToFind){
        ConcurrentHashMap<Integer,ProviderData> data=new ConcurrentHashMap<>();
        try {
            ResultSet resultSet;
            Statement statement;
            String sql = "SELECT * FROM providers ";
            boolean whereIsNotUsed=true;
            if(providerDataToFind.getLocation()!=null){
                sql+="WHERE location = '" + providerDataToFind.getLocation() + "' ";
                whereIsNotUsed=false;
            }else if(providerDataToFind.getRating()!=-1){
                if(whereIsNotUsed) {
                    sql+="WHERE rating = " + providerDataToFind.getRating() + " ";
                }
                else {
                    sql += "AND rating = " +providerDataToFind.getRating() + " ";
                }

            }
            sql+=";";
            statement = DataBaseConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            ProviderData providerData;
            while ( resultSet.next()){
                providerData=new ProviderData();
                providerData.setId(resultSet.getInt("ID"));
                providerData.setName(resultSet.getString("name"));
                providerData.setRating(resultSet.getInt("rating"));
                providerData.setLocation(resultSet.getString("location"));
                providerData.setEmail(resultSet.getString("email"));
                data.put(providerData.getId(),providerData);
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
