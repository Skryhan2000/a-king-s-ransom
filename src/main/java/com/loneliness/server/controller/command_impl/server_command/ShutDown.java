package com.loneliness.server.controller.command_impl.server_command;

import com.loneliness.server.server.Server;
import com.loneliness.server.controller.Command;

public class ShutDown implements Command {
    @Override
    public Object execute(Object request) {
        Server.close();
        return null;
    }
}
