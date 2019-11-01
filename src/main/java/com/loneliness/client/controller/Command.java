package com.loneliness.client.controller;

public interface Command {
    Object execute(Object request) throws ControllerException;
}
