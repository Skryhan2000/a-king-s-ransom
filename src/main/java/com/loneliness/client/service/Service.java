package com.loneliness.client.service;

public interface Service<D, T, S, C> {
    S create(D obj) throws  ServiceException;
    D receive(D obj) throws  ServiceException;
    S update(D obj) throws  ServiceException;
    S delete(D obj) throws  ServiceException;
    C receiveAllElem(T obj) throws  ServiceException;
    C receiveAllElemInLimit(T obj) throws  ServiceException;
}
