package com.loneliness.client.dao;

public interface CRUD <D, T, R, C>{
    R create(D user) throws  DAOException;
    D read(D user) throws  DAOException;
    R update(D user) throws  DAOException;
    R delete(D user) throws  DAOException;
    C receiveAll() throws  DAOException;
    C receiveAllInInterval(T transmission) throws DAOException;
}
