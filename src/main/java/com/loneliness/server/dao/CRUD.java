package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;

public interface CRUD {
    boolean create(Object user);
    Object read(Object user);
    boolean update(Object user);
    boolean delete(Object user);
    Object receiveAll();
    Object receiveAllInLimit(Transmission transmission);
}
