package com.loneliness.client.controller;

import com.loneliness.client.controller.command_impl.Authorize;
import com.loneliness.client.controller.command_impl.CreateUser;
import com.loneliness.client.controller.command_impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider commandProvider=new CommandProvider();
    private CommandProvider(){
        repository.put(CommandName.WRONG_REQUEST,new WrongRequest());
        repository.put(CommandName.CREATE_USER,new CreateUser());
        repository.put(CommandName.AUTHORIZE,new Authorize());
    }

    public Map<CommandName, Command> getRepository() {
        return repository;
    }

    public static CommandProvider getCommandProvider() {
        return commandProvider;
    }

    public Command getCommand(String name){
        CommandName commandName=null;
        Command command=null;
        name=name.replace(" ","_");
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }
        catch (IllegalArgumentException|NullPointerException e){
            command=repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}

