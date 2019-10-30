package com.loneless.server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command {
    Object execute(Object request);
}
