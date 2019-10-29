package com.loneless.server.controller.command_impl;

import com.loneless.server.controller.Command;
import com.loneless.server.entity.user.UserData;
import com.loneless.server.entity.user.UserPrivateData;
import com.loneless.server.logic.Logic;

public class Authorization implements Command {
    @Override
    public boolean execute(Object request) {
        if(request==null){
            return false;
        }
        else if(UserPrivateData.class!=request.getClass()){
            return false;
        }
        return Logic.getInstance().isUserExist((UserPrivateData) request);
    }
}
