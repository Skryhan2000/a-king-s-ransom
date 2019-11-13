package com.loneliness.server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command<Type> {
    Object execute(Type request);
}
