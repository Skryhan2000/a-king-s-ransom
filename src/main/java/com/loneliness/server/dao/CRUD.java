package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;

public interface CRUD<Type> {
    // TODO: 13.11.2019 добавить jeneric
    boolean create(Type obj);
    Object read(Type obj);
    boolean update(Type obj);
    boolean delete(Type obj);
    Object receiveAll();
    Object receiveAllInLimit(Transmission transmission);
}
