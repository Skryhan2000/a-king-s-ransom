package com.loneliness.client.controller.command_impl;

import com.loneliness.client.controller.Command;

public class WrongRequest implements Command<Object,String> {
    @Override
    public String execute(Object request) {
        return "Неверный запрос";
    }
}
