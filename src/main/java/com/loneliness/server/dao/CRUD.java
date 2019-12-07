package com.loneliness.server.dao;


import com.loneliness.entity.transmission.Transmission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CRUD<DataType,CollectionType,InfoType> {
    Logger logger = LogManager.getLogger();
    InfoType create(DataType obj);
    DataType read(DataType obj);
    InfoType update(DataType obj);
    InfoType delete(DataType obj);
    CollectionType receiveAll();
    CollectionType receiveAllInLimit(Transmission transmission);
}
