package com.loneliness.client.service;

import java.io.IOException;

public interface Service {
    Object create(Object obj) throws IOException, ClassNotFoundException;
    Object receive(Object obj) throws IOException, ClassNotFoundException;
    Object update(Object obj) throws IOException, ClassNotFoundException;
    Object delete(Object obj) throws IOException, ClassNotFoundException;
    Object authorization(Object obj) throws IOException, ClassNotFoundException;
    Object receiveAllElem(Object obj) throws IOException, ClassNotFoundException;
}
