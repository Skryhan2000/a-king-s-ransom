package com.loneliness.server.logic;

public interface Service <DataType,CollectionType,InfoType,Transfer>{
    InfoType create(DataType obj);
    DataType receive(DataType obj);
    InfoType update(DataType obj);
    InfoType delete(DataType obj);
    CollectionType receiveAllElem();
    CollectionType receiveAllElemInLimit(Transfer obj);
}
