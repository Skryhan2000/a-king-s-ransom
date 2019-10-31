package com.loneliness.client.dao;


import com.loneliness.entity.user.UserData;

import java.io.IOException;

public interface CRUD {
    boolean create(UserData user) throws IOException, ClassNotFoundException, DAOException;
    UserData read(UserData user) throws IOException, ClassNotFoundException, DAOException;
    boolean update(UserData user) throws IOException, ClassNotFoundException, DAOException;
    boolean delete(UserData user) throws IOException, ClassNotFoundException, DAOException;
    Object receiveAll() throws IOException, ClassNotFoundException, DAOException;
}
