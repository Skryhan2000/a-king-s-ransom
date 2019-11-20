package com.loneliness.client.view.fxml_controller.change_data;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.fxml_controller.Handler;
import com.loneliness.client.view.fxml_controller.WorkWithAlert;
import com.loneliness.entity.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class ChangeProductDataController implements Handler {
    @FXML
    private Stage dialogStage;
    private String action;
    private Product product=new Product();
    private boolean okClicked=false;
    @FXML
    private TextField nameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField unitPriceField;

    public void setDialogStage(Stage dialogStage,String action, int id) {
        this.dialogStage = dialogStage;
        this.action=action;
        product.setOrderId(id);
    }

    @Override
    public boolean isInputValid() {
        Set<ConstraintViolation<Object>> errors = null;
        try {
            product.setName(nameField.getText());
            product.setQuantity(Integer.parseInt(quantityField.getText()));
            product.setUnit_price(Integer.parseInt(unitPriceField.getText()));
            errors = (Set<ConstraintViolation<Object>>)
                    CommandProvider.getCommandProvider().getCommand("ORDER_DATA_VALIDATION").execute(product);
            if (errors.size() == 0) {
                return true;
            } else {
                WorkWithAlert.getInstance().showAlert("Валидация товара", "Ошибка", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                    e.getExceptionMessage().toString(), dialogStage, "ERROR");
            return false;
        }
        catch (NumberFormatException ex){
            WorkWithAlert.getInstance().showAlert("Ошибка", "введены не верные данные",
                    "Была введена строка вместо числа", dialogStage, "ERROR");
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
        if (isInputValid()) {
            try {
                String answer="";
                switch (action) {
                    case "create":
                        answer=(String)CommandProvider.getCommandProvider().getCommand("CREATE_PRODUCT").execute(product);
                        break;
                    case "update":
                        answer=(String)CommandProvider.getCommandProvider().getCommand("UPDATE_PRODUCT").execute(product);
                        break;
                }
                if(WorkWithAlert.getInstance().showAnswer(answer,dialogStage,"Обновления данных" )){
                    goBack();
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Сбой программы", "Целостность нарушена",
                        e.getExceptionMessage().toString(), dialogStage, "ERROR");
            }
        }
    }
    public void setData(Product product) {
        if(product!=null) {
            nameField.setText(product.getName());
            quantityField.setText(String.valueOf(product.getQuantity()));
            unitPriceField.setText(String.valueOf(product.getUnitPrice()));
            this.product=product;
        }
    }
    public boolean isOkClicked() {
        return okClicked;
    }
}
