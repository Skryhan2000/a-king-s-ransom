package com.loneliness.client.dao;

public interface CRUD <DataType,TransferType>{
    boolean create(DataType user) throws  DAOException;
    Object read(DataType user) throws  DAOException;
    boolean update(DataType user) throws  DAOException;
    boolean delete(DataType user) throws  DAOException;
    Object receiveAll() throws  DAOException;
    Object receiveAllInInterval(TransferType transmission) throws DAOException;
}
