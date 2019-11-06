package com.loneliness.client.dao;

public interface CRUD {
    boolean create(Object user) throws  DAOException;
    Object read(Object user) throws  DAOException;
    boolean update(Object user) throws  DAOException;
    boolean delete(Object user) throws  DAOException;
    Object receiveAll() throws  DAOException;
    Object receiveAllInInterval(Object transmission) throws DAOException;
}
