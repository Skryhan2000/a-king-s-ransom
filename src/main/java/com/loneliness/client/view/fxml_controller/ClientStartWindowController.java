package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.entity.orders.OrderCustomerData;
import com.loneliness.entity.transmission.Transmission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

public class ClientStartWindowController implements CRUD_Controller{
    private static int companyID;
    private int  indexOfCurrentValue[]={0,20};
    @FXML private Stage dialogStage;
    @FXML private TableView<OrderCustomerData> orderTable;
    @FXML private TableColumn<OrderCustomerData, String> orderDateOfCompletionColumn;
    @FXML private TableColumn<OrderCustomerData, String> orderStatusColumn;
    private ObservableList<OrderCustomerData> ordersData = FXCollections.observableArrayList();
    @FXML private Text orderId;
    @FXML private Text orderName;
    @FXML private Text orderDateOfReceiving;
    @FXML private Text orderDateOfCompletion;
    @FXML private Text orderStatus;
    @FXML private Text orderPayment;
    @FXML private Hyperlink orderManagerEmail;
    public static void setData(int clientCompanyID){
       companyID=clientCompanyID;
    }

    public ObservableList<OrderCustomerData> setAndGetOrdersData(ConcurrentHashMap<Integer, OrderCustomerData> map) {
        ordersData.addAll(map.values());
        return ordersData;
    }

    @FXML
    private void openEmail(){
        Desktop desktop;
        if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
            try {
                URI mailto = null;
                mailto = new URI("mailto:"+orderManagerEmail.getText()+"?subject=Запрос%20информации%20о%20заказе%20"+
                        orderName.getText());
                desktop.mail(mailto);
            } catch (URISyntaxException e) {
                WorkWithAlert.getInstance().showAlert("Открытие почтового приложения", "Ошибка",
                        "Ошибка формирования сообщения. Отправка email невозможна", dialogStage, "ERROR");
            } catch (IOException e) {
                WorkWithAlert.getInstance().showAlert("Открытие почтового приложения", "Ошибка",
                        "Что то пошло не по плану", dialogStage, "ERROR");
            }

        } else {
            WorkWithAlert.getInstance().showAlert("Открытие почтового приложения", "Ошибка",
                    "Ваша система не поддерживает mailto. Отправка email невозможна", dialogStage, "ERROR");
        }
    }

    @Override
    public boolean goBack() {
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getAuthorisationFormController())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                    "Нарушение целостности программы", "Попробуйте повторить действие позже" +
                            " или принудительно закройте программу",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    private void fillText(OrderCustomerData orderData) {
        if (orderData == null) {
            orderId.setText("");
            orderName.setText("");
            orderDateOfReceiving.setText("");
            orderDateOfCompletion.setText("");
            orderStatus.setText("");
            orderPayment.setText("");
            orderManagerEmail.setText("");
        } else {
            orderId.setText(String.valueOf(orderData.getCustomerId()));
            orderName.setText(orderData.getOrderName());
            orderDateOfReceiving.setText(orderData.getDateOfReceiving().toString());
            orderDateOfCompletion.setText(orderData.getDateOfCompletion().toString());
            orderStatus.setText(orderData.getStatus().toString());
            orderPayment.setText(orderData.getPayment().toString());
            orderManagerEmail.setText(orderData.getManagerEmail());
        }
    }

    @FXML
    private void initialize(){
        orderTable.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, OrderCustomerData, newOrderData) -> fillText( newOrderData)));

        orderDateOfCompletionColumn.setCellValueFactory(orderDataStringCellDataFeatures ->
                orderDataStringCellDataFeatures.getValue().dateOfCompletionStringProperty());
        orderStatusColumn.setCellValueFactory(orderDataStringCellDataFeatures ->
                orderDataStringCellDataFeatures.getValue().statusStringProperty());
        OrderCustomerData orderData = null;
        fillText(orderData);
        update();
    }

    @Override
    public boolean update() {
        Transmission transmission = new Transmission();
        transmission.setFirstIndex(indexOfCurrentValue[0]);
        transmission.setLastIndex(indexOfCurrentValue[1]);
        OrderCustomerData customerData=new OrderCustomerData();
        customerData.setCustomerId(companyID);
        transmission.setOrderCustomerData(customerData);
        ordersData.clear();
        transmission.setCommand("RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT");
        try {
            setAndGetOrdersData((ConcurrentHashMap<Integer, OrderCustomerData>) CommandProvider.
                    getCommandProvider().getCommand("RECEIVE_ALL_CUSTOMER_ORDER_IN_LIMIT")
                    .execute(transmission));
            orderTable.refresh();
            orderTable.setItems(ordersData);
            return true;
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
            return false;
        }

    }

    @Override
    public boolean addHandler() {
        return false;
    }

    @Override
    public boolean deleteHandler() {
        return false;
    }

    @Override
    public boolean changeHandler() {
        return false;
    }

    @Override
    public boolean searchHandler() {
        return false;
    }

    @FXML
    private void addTwentyNode() {
        indexOfCurrentValue[0] += 20;
        indexOfCurrentValue[1] += 20;
        update();
    }

    @FXML
    private void removeTwentyNode() {
        indexOfCurrentValue[0] -= 20;
        indexOfCurrentValue[1] -= 20;
        if (indexOfCurrentValue[0] < 0) {
            indexOfCurrentValue[0] = 0;
            indexOfCurrentValue[1] = 20;
        }
        update();
    }
}
