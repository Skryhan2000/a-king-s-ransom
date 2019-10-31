package com.loneliness.client.controller;


import java.io.IOException;

public interface Command {
    Object execute(Object request) throws IOException, ClassNotFoundException;
}
