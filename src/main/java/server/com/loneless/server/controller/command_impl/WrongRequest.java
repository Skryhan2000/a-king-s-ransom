package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.entity.user.UserPrivateData;

public class WrongRequest implements Command {
    @Override
    public boolean execute(Object request) {
        return false;
    }
}
