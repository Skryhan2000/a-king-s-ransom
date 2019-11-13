package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;

public interface CRUD<DataType,CollectionType,InfoType> {
    InfoType create(DataType obj);
    DataType read(DataType obj);
    InfoType update(DataType obj);
    InfoType delete(DataType obj);
    CollectionType receiveAll();
    CollectionType receiveAllInLimit(Transmission transmission);
}
