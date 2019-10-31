package com.loneliness.client.dao;


import com.loneliness.entity.user.UserData;

public interface CRUD {
    boolean create(UserData user);
    UserData read(UserData user);
    boolean update(UserData user);
    boolean delete(UserData user);
    Object receiveAll();
}
