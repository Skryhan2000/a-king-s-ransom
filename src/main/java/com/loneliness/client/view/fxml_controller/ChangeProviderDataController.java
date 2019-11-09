package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.ProviderData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class ChangeProviderDataController implements Handler{
    @FXML private TextField nameField;
    @FXML private TextField ratingField;
    @FXML private TextField locationField;
    @FXML private TextField emailField;
    @FXML private Stage dialogStage;
    private String action;
    private ProviderData providerData;
    private boolean okClicked=false;

    public void setData(ProviderData providerData){
        nameField.setText(providerData.getName());
        ratingField.setText(String.valueOf(providerData.getRating()));
        locationField.setText(providerData.getLocation());
        emailField.setText(providerData.getEmail());
        if(providerData!=null){
            this.providerData=providerData;
        }
        else {
            this.providerData=new ProviderData();
        }
    }
    public void setDialogStage(Stage dialogStage, String action) {
        this.dialogStage = dialogStage;
        this.action = action;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @Override
    public boolean isInputValid() {
        providerData.setName(nameField.getText());
        providerData.setLocation(locationField.getText());
        providerData.setEmail(emailField.getText());

        Set<ConstraintViolation<Object>> errors = null;
        try {
            providerData.setRating(Integer.parseInt(ratingField.getText()));
            errors = (Set<ConstraintViolation<Object>>)
                    CommandProvider.getCommandProvider().getCommand("PROVIDER_VALIDATION").execute(providerData);
            if (errors.size() == 0) {
                return true;
            } else {
                WorkWithAlert.getInstance().showAlert("Валидация поставщика", "ошибка", errors, dialogStage, "ERROR");
                return false;
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Сбой программы","Целостность нарушена",
                    e.getExceptionMessage().toString() , dialogStage, "ERROR");
        } catch (NumberFormatException e){
            WorkWithAlert.getInstance().showAlert("Валидация",
                    "Не верные входные данные", "Не верный ввод числа", dialogStage, "ERROR");
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
        if (isInputValid()) {
            try {
                switch (action) {
                    case "update":
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("UPDATE_PROVIDER").execute(providerData)) {
                            WorkWithAlert.getInstance().showAlert("Обновления данных",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                            goBack();
                        } else {
                            WorkWithAlert.getInstance().showAlert("Ошибка обновления данных",
                                    "Неизвестная ошибка", "короче что то сломалось на сервере", dialogStage, "ERROR");
                        }
                        break;
                    case "create":
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("CREATE_PROVIDER").execute(providerData)) {
                            WorkWithAlert.getInstance().showAlert("Создание поставщика",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                            goBack();
                        } else {
                            WorkWithAlert.getInstance().showAlert("Ошибка обновления данных",
                                    "Неизвестная ошибка", "короче что то сломалось на сервере", dialogStage, "ERROR");
                        }
                        break;
                }

            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Сбой программы","Целостность нарушена",
                        e.getExceptionMessage().toString() , dialogStage, "ERROR");
            }
        }
    }
}
