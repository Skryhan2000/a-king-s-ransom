package server.controller;


import java.util.concurrent.LinkedBlockingDeque;

public interface Command {
    String execute(String request);
}
