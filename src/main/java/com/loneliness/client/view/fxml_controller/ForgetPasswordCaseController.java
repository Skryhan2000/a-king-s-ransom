package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgetPasswordCaseController implements Controller{
    @FXML private RadioButton receiveQuestion=new RadioButton();
    @FXML private TextField loginField =new TextField();
    @FXML private Label questionLabel=new Label();
    @FXML private TextField answerField =new TextField();
    @FXML private TextField passwordField=new TextField();
    private Stage dialogStage;
    private UserData userData;
    private boolean okClicked = false;


    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.userData=new UserData();
    }
    public void setData(String login){
        userData.setLogin(login);
        loginField.setText(login);
    }
    @Override
    @FXML public void goBack(){
        okClicked = false;
        dialogStage.close();

    }
    @FXML
    public void finishWork()  {
        try {


            if (isInputValid()) {
                if (userData.getSecretQuestion() != null && userData.getSecretQuestion().length() != 0) {
                    if (userData.getSecretQuestion().equals(answerField.getText())) {
                        userData.setLogin(loginField.getText());
                        userData.setPassword(passwordField.getText());
                        userData.setPassword(passwordField.getText());
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("UPDATE_USER").
                                execute(userData)) {
                            WorkWithAlert.getInstance().showAlert("Изменение пароля", "",
                                    "Изменение пароля успешно", dialogStage, "INFORMATION");
                            okClicked = true;
                            dialogStage.close();
                        } else {
                            WorkWithAlert.getInstance().showAlert("Изменение пароля", "Измененить пароль неудалось",
                                    "Непредвиденная ошибка", dialogStage, "ERROR");
                        }
                    } else {
                        WorkWithAlert.getInstance().showAlert("Изменение пароля", "Ошибка валидации",
                                "Неверный ответ", dialogStage, "ERROR");
                    }
                } else {
                    WorkWithAlert.getInstance().showAlert("Изменение пароля", "Неверная последовательность действий",
                            "Сначала получите секретный вопрос", dialogStage, "ERROR");
                }
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Изменение пароля",  e.getExceptionMessage().toString(),
                   "Повторите попыткку позже", dialogStage, "ERROR");
        }

    }

    @FXML private void receiveUserData() throws IOException, ClassNotFoundException {
        try {
            if (receiveQuestion.isSelected()) {
                receiveQuestion.setSelected(true);
            }
            if (loginField.getText() != null && loginField.getText().length() != 0) {
                userData.setLogin(loginField.getText());
                userData = (UserData) CommandProvider.getCommandProvider().getCommand("RECEIVE_USER_DATA").execute(userData);
                questionLabel.setText(userData.getSecretQuestion());
            } else {
                WorkWithAlert.getInstance().showAlert("Неверный ввод",
                        "Исправьте недопустимые поля", "Не допустимый логин", dialogStage, "ERROR");
                receiveQuestion.setSelected(false);
            }
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка", e.getExceptionMessage().toString(),
                   "Повторите попыткку позже" , dialogStage, "ERROR");
        }

    }


    public boolean isInputValid() {
        String errorMessage = "";

        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "Не допустимый логин!\n";
        }
        else if (answerField.getText() == null || answerField.getText().length() == 0) {
            errorMessage += "Не допустимый ответ на вопрос!\n";
        }
        else if(passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "Не допустимый пароль!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Исправьте недопустимые поля",errorMessage,dialogStage,"ERROR");
            return false;
        }
    }

}
