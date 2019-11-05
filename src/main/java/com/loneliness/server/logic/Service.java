package com.loneliness.server.logic;

public interface Service {
    Object create(Object obj);
    Object receive(Object obj);
    Object update(Object obj);
    Object delete(Object obj);
    Object authorization(Object obj);
    Object receiveAllElem();
    Object receiveAllElemInLimit(Object obj);
}
