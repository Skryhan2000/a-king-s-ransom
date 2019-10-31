package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.launcher.Client;
import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AuthorisationFormController {
    @FXML private Stage dialogStage;
    @FXML private Button enterButtton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    @FXML private void authorize(){
        UserData userData=new UserData();
        userData.setLogin(loginField.getText());
        userData.setPassword(passwordField.getText());
        if(!(Boolean) CommandProvider.getCommandProvider().getCommand("AUTHORIZE").execute(userData)){
            showAlert("Ошибка авторизации","Нет такого пользователя","Неверный логин и/или пароль");
        }

    }
    public boolean init()  {
        if (Client.getInObject()==null||Client.getOutObject()==null) {
            showAlert("Ошибка подключения","Подключение к серверу отсутствует","Нажмите ок для переподключения");
            return Client.reconnect();
        }
        return true;
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();

    }
}
