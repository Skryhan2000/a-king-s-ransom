package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.logic.Logic;

public class UpdateUser implements Command {
    @Override
    public Object execute(Object request) {
        return Logic.getInstance().updateUserData((UserData)request);
    }
}
