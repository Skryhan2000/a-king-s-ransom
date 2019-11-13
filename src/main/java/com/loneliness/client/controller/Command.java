package com.loneliness.client.controller;

public interface Command<Type> {
    Object execute(Type request) throws ControllerException;
}
