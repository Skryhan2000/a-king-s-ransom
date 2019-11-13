package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.ProductInStock;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.time.DateTimeException;
import java.util.Set;

public class ChangeProductInStockController implements Handler{
    @FXML private Stage dialogStage;
    private String action;
    private boolean okClicked=false;
    private ProductInStock productInStock=new ProductInStock();
    @FXML private TextField productName;
    @FXML private DatePicker productReceipt_date;
    @FXML private TextField productQuantity;
    @FXML private TextField productUnitPrice;
    @FXML private TextField productProviderId;


    public void setDialogStage(Stage dialogStage, String action) {
        this.dialogStage = dialogStage;
        this.action = action;
    }

    @Override
    public boolean isInputValid() {
        productInStock.setName(productName.getText());
        Set<ConstraintViolation<Object>> errors = null;
        try {
            productInStock.setReceipt_date(productReceipt_date.getValue());
            productInStock.setQuantity(Integer.parseInt(productQuantity.getText()));
            productInStock.setUnitPrice(Double.parseDouble(productUnitPrice.getText()));
            productInStock.setProvider_ID(Integer.parseInt(productProviderId.getText()));

            errors = (Set<ConstraintViolation<Object>>)
                    CommandProvider.getCommandProvider().getCommand("PRODUCT_IN_STOCK_VALIDATION").execute(productInStock);
            if (errors.size() == 0) {
                return true;
            } else {
                WorkWithAlert.getInstance().showAlert("Валидация товара на складе", "Ошибка", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Сбой программы","Целостность нарушена",
                    e.getExceptionMessage().toString() , dialogStage, "ERROR");
        } catch (NumberFormatException e){
            WorkWithAlert.getInstance().showAlert("Валидация",
                    "Не верные входные данные", "Не верный ввод числа", dialogStage, "ERROR");
        }catch (DateTimeException e){
            WorkWithAlert.getInstance().showAlert("Валидация",
                    "Не верные входные данные", "Не верный ввод даты", dialogStage, "ERROR");
        }
        return false;
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
                        if (!(Boolean) CommandProvider.getCommandProvider().getCommand("CREATE_PRODUCT_IN_STOCK").execute(productInStock)) {
                            err=true;
                        }
                        break;
                    case "update":
                        if (!(Boolean) CommandProvider.getCommandProvider().getCommand("UPDATE_PRODUCT_IN_STOCK").execute(productInStock)) {
                            err=true;
                        }
                        break;
                }
                if(err) {
                    WorkWithAlert.getInstance().showAlert("Ошибка обновления данных",
                            "Неизвестная ошибка", "Короче что то случилось на сервере", dialogStage, "ERROR");
                }
                else {
                    WorkWithAlert.getInstance().showAlert("Обновления данных",
                            "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                    goBack();
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                        e.getExceptionMessage().toString(), dialogStage, "ERROR");
            }
        }
    }

    public void setData(ProductInStock productInStock) {
        if(productInStock!=null) {
            productName.setText(productInStock.getName());
            productQuantity.setText(String.valueOf(productInStock.getQuantity()));
            productUnitPrice.setText(String.valueOf(productInStock.getUnitPrice()));
            productProviderId.setText(String.valueOf(productInStock.getProvider_ID()));
            productReceipt_date.setValue(productInStock.getReceipt_date());
            this.productInStock=productInStock;
        }
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
