package com.loneliness.server.logic;

public interface Service {
    Object create(Object obj);
    Object receive(Object obj);
    Object update(Object obj);
    Object delete(Object obj);
    boolean isSuchElemExist(Object obj);
    Object receiveAllElem(Object obj);
}
