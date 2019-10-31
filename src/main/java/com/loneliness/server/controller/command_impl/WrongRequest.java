package com.loneliness.server.controller.command_impl;

import com.loneliness.server.controller.Command;

public class WrongRequest implements Command {
    @Override
    public Object execute(Object request) {
        return false;
    }
}
