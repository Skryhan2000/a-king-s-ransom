package com.loneless.server.dao;

import com.loneless.server.entity.user.UserData;

public interface CRUD {
    boolean create(UserData user);
    UserData read(UserData user);
    boolean update(UserData user);
    boolean delete(UserData user);
}
