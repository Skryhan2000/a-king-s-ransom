package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.CustomerData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeCustomerDataController implements Handler{
    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField locationField;
    @FXML private TextField emailField;

    private Stage dialogStage;
    private String action;
    private boolean okClicked = false;
    private CustomerData customerData=new CustomerData();

    public void setDialogStage(Stage dialogStage, String action) {
        this.dialogStage = dialogStage;
        this.action = action;
    }

    @Override
    public boolean isInputValid() {
        Set<ConstraintViolation<Object>> errors = null;
        try {
            customerData.setNumberOfOrders(Integer.parseInt(quantityField.getText()));
            customerData.setName(nameField.getText());
            customerData.setLocation(locationField.getText());
            customerData.setEmail(emailField.getText());
            errors = (Set<ConstraintViolation<Object>>)
                    CommandProvider.getCommandProvider().getCommand("CUSTOMER_DATA_VALIDATION").execute(customerData);
            if (errors.size() == 0) {
                return true;
            } else {
                WorkWithAlert.getInstance().showAlert("Валидация заказа", "Ошибка", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                    e.getExceptionMessage().toString(), dialogStage, "ERROR");
            return false;
        }
    }

    @Override
    public void goBack() {
        okClicked = true;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        boolean err=false;
        if (isInputValid()) {
            try {
                switch (action) {
                    case "create":
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("CREATE_CUSTOMER_DATA").execute(customerData)) {
                            WorkWithAlert.getInstance().showAlert("Обновления данных",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                            goBack();
                        }
                        else err=true;
                        break;
                    case "update":
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("UPDATE_CUSTOMER_DATA").execute(customerData)) {
                            WorkWithAlert.getInstance().showAlert("Обновления данных",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                            goBack();
                        }
                        else err=true;
                        break;
                }
                if(err) {
                    WorkWithAlert.getInstance().showAlert("Ошибка обновления данных",
                            "Неизвестная ошибка", "Короче что то случилось на сервере", dialogStage, "ERROR");
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                        e.getExceptionMessage().toString(), dialogStage, "ERROR");
            }
        }
    }

    public void setData(CustomerData customerData) {
        this.customerData = customerData;
        nameField.setText(customerData.getName());
        quantityField.setText(String.valueOf(customerData.getNumberOfOrders()));
        locationField.setText(customerData.getLocation());
        emailField.setText(customerData.getEmail());
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
