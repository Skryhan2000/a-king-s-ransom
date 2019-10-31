package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.launcher.Client;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class AuthorisationFormController {
    @FXML private Stage dialogStage;
    @FXML private Button enterButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    @FXML private void authorize() throws ClassNotFoundException {
        UserData userData = new UserData();
        userData.setLogin(loginField.getText());
        userData.setPassword(passwordField.getText());
        try {
            switch ((String) CommandProvider.getCommandProvider().getCommand("AUTHORIZE").execute(userData)) {
                case "admin":
                    PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                            getInstance().getAdminStartWindow())));
                    break;
                case "manager":
                    PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                            getInstance().getManagerStartWindow())));
                    break;
                case "client":
                    PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                            getInstance().getClientStartWindow())));
                    break;
                case "noType":
                    WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                            "Нет такого пользователя", "Неверный логин и/или пароль",
                            dialogStage,"ERROR");
                    break;
                default:
                    WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                            "Неизвестная ошибка", "авторизуйтесь позже",dialogStage,"ERROR");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML private void onKeyTyped(){
        PrimaryStage.getInstance().getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    authorize();
                } catch (ClassNotFoundException e) {
                    WorkWithAlert.getInstance().showAlert("Ошибка подключения",
                            "Неизвестная ошибка",
                            "Попробуйте повторить действие позже",dialogStage,"ERROR");
                }

            }
        });
    }
    public boolean init()  {
        if (Client.getInObject()==null||Client.getOutObject()==null) {
            WorkWithAlert.getInstance().showAlert("Ошибка подключения",
                    "Подключение к серверу отсутствует",
                    "Нажмите ок для переподключения",dialogStage,"ERROR");
            return Client.reconnect();
        }
        return true;
    }


    public boolean showForgetPasswordCase() throws IOException {
        Stage dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                getForgetYourPasswordCase(),"Востановления пароля");
        ForgetPasswordCaseController controller=WorkWithFXMLLoader.getInstance().getLoader().getController();
        controller.setDialogStage(dialogStage);
        controller.setData(loginField.getText());
        dialogStage.showAndWait();
        return controller.isOkClicked();
    }

}
