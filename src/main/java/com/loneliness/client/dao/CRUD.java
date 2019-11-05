package com.loneliness.client.dao;


import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public interface CRUD {
    boolean create(UserData user) throws IOException, ClassNotFoundException, DAOException;
    UserData read(UserData user) throws IOException, ClassNotFoundException, DAOException;
    boolean update(UserData user) throws IOException, ClassNotFoundException, DAOException;
    boolean delete(UserData user) throws IOException, ClassNotFoundException, DAOException;
    Object receiveAll() throws IOException, ClassNotFoundException, DAOException;
    Object receiveAllInInterval(Transmission transmission) throws DAOException;
}
