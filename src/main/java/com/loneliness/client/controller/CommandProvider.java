package com.loneliness.client.controller;



import com.loneliness.client.controller.command_impl.customer_command.*;
import com.loneliness.client.controller.command_impl.product_command.*;
import com.loneliness.client.controller.command_impl.product_in_stock_command.*;
import com.loneliness.client.controller.command_impl.report_command.CreateReport;
import com.loneliness.client.controller.command_impl.report_command.PrintReport;
import com.loneliness.client.controller.command_impl.validation_command.*;
import com.loneliness.client.controller.command_impl.WrongRequest;
import com.loneliness.client.controller.command_impl.order_command.*;
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
        repository.put(CommandName.USER_DATA_VALIDATION,new UserDataValidation());
        repository.put(CommandName.USER_PRIVATE_DATA_VALIDATION,new UserPrivateDataValidation());


        repository.put(CommandName.CREATE_PROVIDER,new CreateProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS_IN_LIMIT,new ReceiveAllProvidersInLimit());
        repository.put(CommandName.DELETE_PROVIDER,new DeleteProvider());
        repository.put(CommandName.RECEIVE_ALL_PROVIDERS,new ReceiveAllProviders());
        repository.put(CommandName.RECEIVE_PROVIDER_DATA,new ReceiveProviderData());
        repository.put(CommandName.UPDATE_PROVIDER,new UpdateProvider());
        repository.put(CommandName.FIND_PROVIDER_BY_LOCATION_AND_RATING,new FindProviderByLocationAndRating());
        repository.put(CommandName.PROVIDER_VALIDATION,new ProviderValidation());
        repository.put(CommandName.FIND_PROVIDER_BY_LOCATION_RATING_AND_VALUE,new FindProviderByLocationRatingAndValue());

        repository.put(CommandName.CREATE_ORDER,new CreateOrder());
        repository.put(CommandName.DELETE_ORDER,new DeleteOrder());
        repository.put(CommandName.FIND_ALL_ORDERS_BY_DATE_OF_COMPLETION_AND_STATUS,new FindAllOrdersByDateOfCompletionAndStatus());
        repository.put(CommandName.RECEIVE_ALL_ORDERS_IN_LIMIT,new ReceiveAllOrdersInLimit());
        repository.put(CommandName.RECEIVE_ALL_ORDERS,new ReceiveAllOrders());
        repository.put(CommandName.RECEIVE_ORDER_DATA,new ReceiveOrderData());
        repository.put(CommandName.UPDATE_ORDER,new UpdateOrder());
        repository.put(CommandName.ORDER_DATA_VALIDATION,new OrderDataValidation());
        repository.put(CommandName.RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT,new ReceiveAllCustomerOrderInLimit());
        repository.put(CommandName.SEARCH_FOR_BURNING_ORDERS,new SearchForBurningOrders());
        repository.put(CommandName.CALCULATE_SUM_OF_ORDER,new CalculateSumOfOrder());


        repository.put(CommandName.CREATE_PRODUCT_IN_STOCK,new CreateProductInStock());
        repository.put(CommandName.DELETE_PRODUCT_IN_STOCK,new DeleteProductInStock());
        repository.put(CommandName.FIND_ALL_PRODUCT_IN_STOCK_BY_NAME_AND_QUANTITY,new FindAllProductInStockByNameAndQuantity());
        repository.put(CommandName.RECEIVE_ALL_PRODUCT_IN_STOCK_IN_LIMIT,new ReceiveAllProductInStockInLimit());
        repository.put(CommandName.RECEIVE_ALL_PRODUCT_IN_STOCK,new ReceiveAllProductInStock());
        repository.put(CommandName.RECEIVE_PRODUCT_IN_STOCK,new ReceiveProductInStock());
        repository.put(CommandName.UPDATE_PRODUCT_IN_STOCK,new UpdateProductInStock());
        repository.put(CommandName.PRODUCT_IN_STOCK_VALIDATION,new ProductInStockValidation());

        repository.put(CommandName.CUSTOMER_DATA_VALIDATION,new CustomerDataValidation());
        repository.put(CommandName.CREATE_CUSTOMER_DATA,new CreateCustomerData());
        repository.put(CommandName.DELETE_CUSTOMER_DATA,new DeleteCustomerData());
        repository.put(CommandName.FIND_ALL_CUSTOMERS_DATA_BY_NAME_AND_NUMBER_OF_ORDERS,new FindAllCustomersDataByNameAndNumberOfOrders());
        repository.put(CommandName.RECEIVE_ALL_CUSTOMERS_DATA,new ReceiveAllCustomersData());
        repository.put(CommandName.RECEIVE_ALL_CUSTOMERS_DATA_IN_LIMIT,new ReceiveAllCustomersDataInLimit());
        repository.put(CommandName.RECEIVE_CUSTOMER_DATA,new ReceiveCustomerData());
        repository.put(CommandName.UPDATE_CUSTOMER_DATA,new UpdateCustomerData());
        repository.put(CommandName.PRODUCT_DATA_VALIDATION,new ProductDataValidation());

        repository.put(CommandName.CREATE_REPORT,new CreateReport());
        repository.put(CommandName.PRINT_REPORT,new PrintReport());

        repository.put(CommandName.CALCULATE_SUM_OF_PRODUCT,new CalculateSumOfProduct());
        repository.put(CommandName.CREATE_PRODUCT,new CreateProduct());
        repository.put(CommandName.DELETE_PRODUCT,new DeleteProduct());
        repository.put(CommandName.RECEIVE_ALL_PRODUCTS,new ReceiveAllProducts());
        repository.put(CommandName.RECEIVE_ALL_PRODUCTS_IN_LIMIT,new ReceiveAllProductsInLimit());
        repository.put(CommandName.RECEIVE_PRODUCT,new ReceiveProduct());
        repository.put(CommandName.UPDATE_PRODUCT,new UpdateProduct());
        repository.put(CommandName.RECEIVE_PRODUCT_GOODS,new ReceiveProductGoods());

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

