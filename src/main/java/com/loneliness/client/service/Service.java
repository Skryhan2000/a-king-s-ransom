package com.loneliness.client.service;

import java.io.IOException;

public interface Service<DataType,TransferType,String,collectionType> {
    String create(DataType obj) throws  ServiceException;
    DataType receive(DataType obj) throws  ServiceException;
    String update(DataType obj) throws  ServiceException;
    String delete(DataType obj) throws  ServiceException;
    collectionType receiveAllElem(TransferType obj) throws  ServiceException;
    collectionType receiveAllElemInLimit(TransferType obj) throws  ServiceException;
}
