package com.loneliness.client.dao;


import com.loneliness.entity.user.UserData;

import java.io.IOException;

public interface CRUD {
    boolean create(UserData user) throws IOException, ClassNotFoundException;
    UserData read(UserData user) throws IOException, ClassNotFoundException;
    boolean update(UserData user) throws IOException, ClassNotFoundException;
    boolean delete(UserData user) throws IOException, ClassNotFoundException;
    Object receiveAll() throws IOException, ClassNotFoundException;
}
