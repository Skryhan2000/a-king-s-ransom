package com.loneliness.server.controller;





import com.loneliness.server.controller.command_impl.customer_command.*;
import com.loneliness.server.controller.command_impl.order_command.*;
import com.loneliness.server.controller.command_impl.order_command.UpdateOrder;
import com.loneliness.server.controller.command_impl.product_in_stock_command.*;
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
        repository.put(CommandName.RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT,new ReceiveAllCustomerOrderInLimit());

        repository.put(CommandName.CREATE_PRODUCT_IN_STOCK,new CreateProductInStock());
        repository.put(CommandName.DELETE_PRODUCT_IN_STOCK,new DeleteProductInStock());
        repository.put(CommandName.FIND_ALL_PRODUCT_IN_STOCK_BY_NAME_AND_QUANTITY,new FindAllProductInStockByNameAndQuantity());
        repository.put(CommandName.RECEIVE_ALL_PRODUCT_IN_STOCK_IN_LIMIT,new ReceiveAllProductInStockInLimit());
        repository.put(CommandName.RECEIVE_ALL_PRODUCT_IN_STOCK,new ReceiveAllProductInStock());
        repository.put(CommandName.RECEIVE_PRODUCT_IN_STOCK,new ReceiveProductInStock());
        repository.put(CommandName.UPDATE_PRODUCT_IN_STOCK,new UpdateProductInStock());

        repository.put(CommandName.CREATE_CUSTOMER_DATA,new CreateCustomerData());
        repository.put(CommandName.DELETE_CUSTOMER_DATA,new DeleteCustomerData());
        repository.put(CommandName.FIND_ALL_CUSTOMERS_DATA_BY_NAME_AND_NUMBER_OF_ORDERS,new FindAllCustomersDataByNameAndNumberOfOrders());
        repository.put(CommandName.RECEIVE_ALL_CUSTOMERS_DATA,new ReceiveAllCustomersData());
        repository.put(CommandName.RECEIVE_ALL_CUSTOMERS_DATA_IN_LIMIT,new ReceiveAllCustomersDataInLimit());
        repository.put(CommandName.RECEIVE_CUSTOMER_DATA,new ReceiveCustomerData());
        repository.put(CommandName.UPDATE_CUSTOMER_DATA,new UpdateCustomerData());
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

