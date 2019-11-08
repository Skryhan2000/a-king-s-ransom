package com.loneliness.server.controller;





import com.loneliness.server.controller.command_impl.order_command.*;
import com.loneliness.server.controller.command_impl.provider_command.*;
import com.loneliness.server.controller.command_impl.server_command.ShutDown;
import com.loneliness.server.controller.command_impl.WrongRequest;

import com.loneliness.server.controller.command_impl.user_command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static final CommandProvider commandProvider=new CommandProvider();
    private CommandProvider(){
        repository.put(CommandName.WRONG_REQUEST,new WrongRequest());
        repository.put(CommandName.AUTHORIZATION_USER,new AuthorizationUser());
        repository.put(CommandName.CREATE_USER,new CreateUser());
        repository.put(CommandName.DELETE_USER,new DeleteUser());
        repository.put(CommandName.RECEIVE_ALL_USERS,new ReceiveAllUsers());
        repository.put(CommandName.RECEIVE_USER,new ReceiveUser());
        repository.put(CommandName.UPDATE_USER,new UpdateUser());
        repository.put(CommandName.SHUT_DOWN,new ShutDown());
        repository.put(CommandName.FIND_USERS_BY_LOGIN_AND_TYPE,new FindAllByLoginAndType());
        repository.put(CommandName.RECEIVE_ALL_USERS_IN_LIMIT,new ReceiveAllUsersInLimit());

        repository.put(CommandName.CREATE_PROVIDER,new CreateProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS_IN_LIMIT,new ReceiveAllProvidersInLimit());
        repository.put(CommandName.DELETE_PROVIDER,new DeleteProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS,new ReceiveAllProviders());
        repository.put(CommandName.RECEIVE_PROVIDER_DATA,new ReceiveProviderData());
        repository.put(CommandName.UPDATE_PROVIDER,new UpdateProvider());
        repository.put(CommandName.FIND_PROVIDER_BY_LOCATION_AND_RATING,new FindProviderByLocationAndRating());

        repository.put(CommandName.CREATE_ORDER,new CreateOrder());
        repository.put(CommandName.DELETE_ORDER,new DeleteOrder());
        repository.put(CommandName.FIND_ALL_ORDERS_BY_DATE_OF_COMPLETION_AND_STATUS,new FindAllOrdersByDateOfCompletionAndStatus());
        repository.put(CommandName.RECEIVE_ALL_ORDERS_IN_LIMIT,new ReceiveAllOrdersInLimit());
        repository.put(CommandName.RECEIVE_ALL_ORDERS,new ReceiveAllOrders());
        repository.put(CommandName.RECEIVE_ORDER_DATA,new ReceiveOrderData());
        repository.put(CommandName.UPDATE_ORDER,new UpdateOrder());
    }


    public Map<CommandName, Command> getRepository() {
        return repository;
    }

    public static CommandProvider getCommandProvider() {
        return commandProvider;
    }

    public Command getCommand(String name){
        CommandName commandName;
        Command command;
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

