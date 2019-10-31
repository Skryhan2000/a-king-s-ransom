package com.loneliness.server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command {
    Object execute(Object request);
}
