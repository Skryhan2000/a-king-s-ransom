package com.loneliness.client.service;

import java.io.IOException;

public interface Service<DataType,TransferType> {
    Object create(DataType obj) throws  ServiceException;
    Object receive(DataType obj) throws  ServiceException;
    Object update(DataType obj) throws  ServiceException;
    Object delete(DataType obj) throws  ServiceException;
    Object receiveAllElem(TransferType obj) throws  ServiceException;
    Object receiveAllElemInLimit(TransferType obj) throws  ServiceException;
}
