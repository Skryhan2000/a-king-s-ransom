package com.loneliness.client.controller;

import com.loneliness.client.controller.command_impl.*;
import com.loneliness.client.controller.command_impl.provider_command.*;
import com.loneliness.client.controller.command_impl.user_command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider commandProvider=new CommandProvider();
    private CommandProvider(){
        repository.put(CommandName.WRONG_REQUEST,new WrongRequest());
        repository.put(CommandName.CREATE_USER,new CreateUser());
        repository.put(CommandName.AUTHORIZE,new Authorize());
        repository.put(CommandName.DELETE_USER,new DeleteUser());
        repository.put(CommandName.RECEIVE_ALL_USERS,new ReceiveAllUsers());
        repository.put(CommandName.RECEIVE_USER_DATA,new ReceiveUserData());
        repository.put(CommandName.UPDATE_USER,new UpdateUser());
        repository.put(CommandName.FIND_USERS_BY_LOGIN_AND_TYPE,new FindUsersByLoginAndType());
        repository.put(CommandName.RECEIVE_ALL_USERS_IN_LIMIT,new ReceiveAllUsersInLimit());
        repository.put(CommandName.CREATE_PROVIDER,new CreateProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS_IN_LIMIT,new ReceiveAllProvidersInLimit());
        repository.put(CommandName.DELETE_PROVIDER,new DeleteProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS,new ReceiveAllProviders());
        repository.put(CommandName.RECEIVE_PROVIDER_DATA,new ReceiveProviderData());
        repository.put(CommandName.UPDATE_PROVIDER,new UpdateProvider());
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

