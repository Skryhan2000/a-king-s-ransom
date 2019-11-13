package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.CustomerData;
import com.loneliness.entity.orders.OrderData;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.user.UserData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;


public class SearchWindowController implements Handler{
    @FXML private Label name;
    @FXML private Label name1;
    @FXML private TextField textField;
    @FXML private TextField textField1;
    @FXML private Stage dialogStage;

    private ProviderData providerData=new ProviderData();
    private UserData userData=new UserData();
    private OrderData orderData=new OrderData();
    private ProductInStock productInStock=new ProductInStock();
    private CustomerData customerData=new CustomerData();

    private boolean okClicked = false;
    private TableView<UserData> usersTable;
    private ObservableList<UserData> usersData;
    private String type;
    private TableView<ProviderData> providersTable;
    private ObservableList<ProviderData> providersData;
    private TableView<OrderData> ordersTable;
    private ObservableList<OrderData> ordersData;
    private TableView<ProductInStock> productInStockTable;
    private ObservableList<ProductInStock> productsInStockData;
    private TableView<CustomerData> customerTable;
    private ObservableList<CustomerData> customersData;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStageUser(Stage dialogStage, TableView<UserData> usersTable, ObservableList<UserData> usersData, String type) {
        this.dialogStage = dialogStage;
        this.usersTable = usersTable;
        this.usersData = usersData;
        this.type = type;
        name.setText("Логин");
        name1.setText("Права доступа");

    }

    public void setDialogStageProviders(Stage dialogStage, TableView<ProviderData> providersTable, ObservableList<ProviderData> providersData, String type) {
        this.dialogStage = dialogStage;
        this.providersTable=providersTable;
        this.providersData=providersData;
        this.type=type;
        name.setText("Местоположение");
        name1.setText("Рейтинг");

    }

    public void setDialogStageOrders(Stage dialogStage, TableView<OrderData> ordersTable, ObservableList<OrderData> ordersData, String type){
        this.dialogStage = dialogStage;
        this.ordersTable=ordersTable;
        this.ordersData=ordersData;
        this.type=type;
        name1.setText("Статус");
        name.setText("Дедлайн");
    }
    public void setDialogStageProductInStock(Stage dialogStage, TableView<ProductInStock> productInStockTable, ObservableList<ProductInStock> productsInStockData, String type){
        this.dialogStage = dialogStage;
        this.productInStockTable=productInStockTable;
        this.productsInStockData=productsInStockData;
        this.type=type;
        name.setText("Имя");
        name1.setText("Количество");
    }

    public void setDialogCustomerData(Stage dialogStage, TableView<CustomerData> customerTable, ObservableList<CustomerData> customersData, String type){
        this.dialogStage = dialogStage;
        this.customerTable=customerTable;
        this.customersData=customersData;
        this.type=type;
        name.setText("Имя");
        name1.setText("Количество заказов");
    }

    @Override
    public boolean isInputValid() {
        boolean valid = false;
        if (textField.getText() != null && textField.getText().length() != 0) {
            valid = true;
        }
        if (textField1.getText() != null && textField1.getText().length() != 0) {
            switch (type) {
                case "providers":
                    try {

                        if (Integer.parseInt(textField1.getText()) >= 0) {
                            valid = true;
                        } else {
                            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                    "Ошибка проверки введеных данных", "В поле рейтинг должно быть не отрицательное число",
                                    dialogStage, "ERROR");
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                "Ошибка проверки введеных данных", "В воле рейтинг должно быть число",
                                dialogStage, "ERROR");
                    }
                    break;
                case "orders":
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                        orderData.setDateOfCompletion(LocalDate.parse(textField1.getText(), formatter));
                    } catch (DateTimeException e) {
                        WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                "Ошибка проверки введеных данных", "В воле дедлайн должна быть дата",
                                dialogStage, "ERROR");
                    }
                    break;
                case "product_in_stock":
                    try {
                        productInStock.setQuantity(Integer.parseInt(textField1.getText()));
                        if (productInStock.getQuantity() >= 0) {
                            valid = true;
                        } else {
                            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                    "Ошибка проверки введеных данных", "В поле количество должно быть не отрицательное число",
                                    dialogStage, "ERROR");
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                "Ошибка проверки введеных данных", "В поле количество должно быть число",
                                dialogStage, "ERROR");
                    }

                    break;
                case "customers":
                    try {
                        customerData.setNumberOfOrders(Integer.parseInt(textField1.getText()));
                        if (customerData.getNumberOfOrders() >= 0) {
                            valid = true;
                        } else {
                            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                    "Ошибка проверки введеных данных", "В поле количество должно быть не отрицательное число",
                                    dialogStage, "ERROR");
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        WorkWithAlert.getInstance().showAlert("Неверный ввод",
                                "Ошибка проверки введеных данных", "В поле количество должно быть число",
                                dialogStage, "ERROR");
                    }

                    break;
            }
        }
        return valid;
    }
    private void setData(UserData userData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            userData.setLogin(textField.getText());
        }
        if(textField1.getText()!=null&& textField1.getText().length()!=0) {
            userData.setType(textField1.getText());
        }
    }
    private void setData(ProviderData providerData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            providerData.setLocation(textField.getText());
        }
        if(textField1.getText()!=null&& textField1.getText().length()!=0) {
            providerData.setRating(Integer.parseInt(textField1.getText()));
        }
        else  providerData.setRating(-1);
    }
    private void setData(OrderData orderData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            orderData.setStatus(textField.getText());
        }
    }
    private void setData(ProductInStock productInStock){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            productInStock.setName(textField.getText());
        }
    }
    private void setData(CustomerData customerData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            customerData.setName(textField.getText());
        }
    }

    @Override
    public void goBack() {
        okClicked = false;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        if(isInputValid()){
            boolean noData=false;
            try {
                switch (type) {
                    case "users":
                        setData(userData);
                        ConcurrentHashMap<Integer, UserData> userMap = (ConcurrentHashMap<Integer, UserData>) CommandProvider.
                            getCommandProvider().getCommand("FIND_USERS_BY_LOGIN_AND_TYPE").execute(userData);
                     if(userMap.size()!=0) {
                        usersData.clear();
                        usersData.addAll(userMap.values());
                        usersTable.refresh();
                        usersTable.setItems(usersData);
                    }
                     else noData=true;
                     break;
                    case "providers":
                        setData(providerData);
                        ConcurrentHashMap<Integer, ProviderData> providerMap = (ConcurrentHashMap<Integer, ProviderData>) CommandProvider.
                                getCommandProvider().getCommand("FIND_PROVIDER_BY_LOCATION_AND_RATING").execute(providerData);
                        if(providerMap.size()!=0) {
                            providersData.clear();
                            providersData.addAll(providerMap.values());
                            providersTable.refresh();
                            providersTable.setItems(providersData);
                        }
                        else noData=true;
                        break;
                    case "orders":
                        setData(orderData);
                        ConcurrentHashMap<Integer, OrderData> orderMap = (ConcurrentHashMap<Integer, OrderData>) CommandProvider.
                                getCommandProvider().getCommand("FIND_ALL_ORDERS_BY_DATE_OF_COMPLETION_AND_STATUS").execute(orderData);
                        if(orderMap.size()!=0) {
                            ordersData.clear();
                            ordersData.addAll(orderMap.values());
                            ordersTable.refresh();
                            ordersTable.setItems(ordersData);
                        }
                        else noData=true;
                        break;
                    case "product_in_stock":
                        setData(productInStock);
                        ConcurrentHashMap<Integer, ProductInStock> productInStockMap = (ConcurrentHashMap<Integer, ProductInStock>) CommandProvider.
                                getCommandProvider().getCommand("FIND_ALL_PRODUCT_IN_STOCK_BY_NAME_AND_QUANTITY").execute(productInStock);
                        if(productInStockMap.size()!=0) {
                            productsInStockData.clear();
                            productsInStockData.addAll(productInStockMap.values());
                            productInStockTable.refresh();
                            productInStockTable.setItems(productsInStockData);
                        }
                        else noData=true;
                        break;
                    case "customers":
                        setData(customerData);
                        ConcurrentHashMap<Integer, CustomerData> customerDataMap = (ConcurrentHashMap<Integer, CustomerData>) CommandProvider.
                                getCommandProvider().getCommand("FIND_ALL_CUSTOMERS_DATA_BY_NAME_AND_NUMBER_OF_ORDERS").execute(customerData);
                        if(customerDataMap.size()!=0) {
                            customersData.clear();
                            customersData.addAll(customerDataMap.values());
                            customerTable.refresh();
                            customerTable.setItems(customersData);
                        }
                        else noData=true;
                        break;
                }

                if(noData){
                    WorkWithAlert.getInstance().showAlert("Поиск данных",
                            "Отчет о поиске", "Данных нет", dialogStage, "INFORMATION");
                }
                else {
                    goBack();
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка", e.getExceptionMessage().toString(),
                        "Повторите попыткку позже" , dialogStage, "ERROR");
            }
        }else {
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Ошибка проверки введеных данных", "Как минимум 1 поле должно быть введено",
                    dialogStage, "ERROR");
        }
    }
}
