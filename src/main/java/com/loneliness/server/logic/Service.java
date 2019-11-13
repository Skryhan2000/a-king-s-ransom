package com.loneliness.server.logic;

public interface Service <Type>{
    Object create(Type obj);
    Object receive(Type obj);
    Object update(Type obj);
    Object delete(Type obj);
    Object receiveAllElem();
    Object receiveAllElemInLimit(Object obj);
}
