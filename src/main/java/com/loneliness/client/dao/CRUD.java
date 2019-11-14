package com.loneliness.client.dao;

public interface CRUD <DataType,TransferType,returnType,collectionType>{
    returnType create(DataType user) throws  DAOException;
    DataType read(DataType user) throws  DAOException;
    returnType update(DataType user) throws  DAOException;
    returnType delete(DataType user) throws  DAOException;
    collectionType receiveAll() throws  DAOException;
    collectionType receiveAllInInterval(TransferType transmission) throws DAOException;
}
