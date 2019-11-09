package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.launcher.Client;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.client.view.ViewException;
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


public class AuthorisationFormController implements Handler {
    @FXML
    private Stage dialogStage;
    @FXML
    private Button enterButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void finishWork()  {
        if (isInputValid()) {
            UserData userData = new UserData();
            userData.setLogin(loginField.getText());
            userData.setPassword(passwordField.getText());
            try {
                switch ((UserData.Type) CommandProvider.getCommandProvider().getCommand("AUTHORIZE").execute(userData)) {
                    case ADMIN:
                        PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                                getInstance().getAdminStartWindow())));
                        break;
                    case MANAGER:
                        PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                                getInstance().getManagerStartWindow())));
                        break;
                    case CLIENT:
                        PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                                getInstance().getClientStartWindow())));
                        break;
                    case NO_TYPE:
                        WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                                "Нет такого пользователя или ваш статус еще не подтвержден", "Неверный логин и/или пароль",
                                dialogStage, "ERROR");
                        break;
                    default:
                        WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                                "Неизвестная ошибка", "авторизуйтесь позже", dialogStage, "ERROR");
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                        e.getExceptionMessage().toString(), "авторизуйтесь позже", dialogStage, "ERROR");
            } catch (IOException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка регистрации",
                        "Неизвестная ошибка", "Попробуйте повторить действие позже",
                        dialogStage, "ERROR");
            }
        }

    }

    @FXML
    private void onKeyTyped() {
        PrimaryStage.getInstance().getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                    finishWork();

            }
        });
    }

    public boolean init() {
        if (Client.getInObject() == null || Client.getOutObject() == null) {
            WorkWithAlert.getInstance().showAlert("Ошибка подключения",
                    "Подключение к серверу отсутствует",
                    "Нажмите ок для переподключения", dialogStage, "ERROR");
            return Client.reconnect();
        }
        return true;
    }


    public boolean showForgetPasswordCase()  {
        Stage dialogStage = null;
        try {
            dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                    getForgetYourPasswordCase(), "Востановления пароля");
            ForgetPasswordCaseHandler controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
            controller.setDialogStage(dialogStage);
            controller.setData(loginField.getText());
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (ViewException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка восстановления пароля",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
return false;
    }

    @FXML
    public void showRegistrationForm() {
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getRegistrationForm())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка авторизации",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    dialogStage, "ERROR");
        }
    }

    @Override
    public boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Не допустимый логин!\n";
        } else if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Не допустимый пароль!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Исправьте недопустимые поля", errorMessage, dialogStage, "ERROR");
            return false;
        }
    }

    @Override
    public void goBack() {

    }
}
