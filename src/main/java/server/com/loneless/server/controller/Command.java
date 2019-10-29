package com.loneless.server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command {
    boolean execute(Object request);
}
