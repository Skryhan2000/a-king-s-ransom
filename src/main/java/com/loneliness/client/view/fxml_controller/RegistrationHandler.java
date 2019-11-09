package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationHandler implements Handler {
    @FXML
    private TextField loginField = new TextField();
    @FXML
    private PasswordField passwordField = new PasswordField();
    @FXML
    private TextField questionField = new TextField();
    @FXML
    private TextField answerField = new TextField();
    @FXML
    private Stage dialogStage;

    @FXML
    public void finishWork() {
        if (isInputValid()) {
            try {
                UserData userData = new UserData();
                userData.setLogin(loginField.getText());
                userData.setPassword(passwordField.getText());
                userData.setSecretQuestion(questionField.getText());
                userData.setSecretAnswer(answerField.getText());
                userData.setType(userData.getType("NO_TYPE"));
                if((Boolean) CommandProvider.getCommandProvider().getCommand("CREATE_USER").execute(userData)){
                    WorkWithAlert.getInstance().showAlert("Регистрация пользователя", "Регистрация успешна",
                            "Добро пожаловать", dialogStage, "INFORMATION");
                }
                else {
                    WorkWithAlert.getInstance().showAlert("Регистрация пользователя", "Регистрация не завершена",
                            "Попробуйте зарегистрироваться позже", dialogStage, "ERROR");
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка", e.getExceptionMessage().toString(),
                        "Повторите попыткку позже", dialogStage, "ERROR");
            }
        }
    }

    @Override
    public boolean isInputValid() {
        StringBuilder errorMessage=new StringBuilder();

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage.append("Не допустимый логин!\n");
        } else if (answerField.getText() == null || answerField.getText().length() == 0) {
            errorMessage.append("Не допустимый ответ на вопрос!\n");
        } else if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage.append("Не допустимый пароль!\n");
        } else if(questionField.getText() == null ||questionField.getText().length() == 0) {
            errorMessage.append("Не допустимый ответ!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Исправьте недопустимые поля", errorMessage.toString(), dialogStage, "ERROR");
            return false;
        }
    }

    @Override
    public void goBack() {
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getAuthorisationFormController())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка регистрации",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    dialogStage, "ERROR");
        }
    }
}
