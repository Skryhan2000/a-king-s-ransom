package com.loneliness.client.service;

public interface Service {
    Object create(Object obj);
    Object receive(Object obj);
    Object update(Object obj);
    Object delete(Object obj);
    Object authorization(Object obj);
    Object receiveAllElem(Object obj);
}
