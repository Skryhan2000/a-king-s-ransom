package com.loneliness.client.service;

import java.io.IOException;

public interface Service {
    Object create(Object obj) throws  ServiceException;
    Object receive(Object obj) throws  ServiceException;
    Object update(Object obj) throws  ServiceException;
    Object delete(Object obj) throws  ServiceException;
    Object authorization(Object obj) throws  ServiceException;
    Object receiveAllElem(Object obj) throws  ServiceException;
}
