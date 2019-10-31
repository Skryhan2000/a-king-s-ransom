package com.loneliness.client.view.fxml_controller;

import com.loneliness.entity.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgetPasswordCaseController {
    @FXML private TextField loginField =new TextField();
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
        if(userData.getLogin()!=null&&userData.getLogin().length()!=0){
            loginField.setText(userData.getLogin());
        }
    }
    public void setData(String login){
        userData.setLogin(login);
    }

    public void CancelChanging(){
        okClicked = false;
        dialogStage.close();

    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if(userData.getSecretQuestion()!=null&&userData.getSecretQuestion().length()!=0) {
                if(userData.getSecretQuestion().equals(answerField.getText())) {
                    userData.setLogin(loginField.getText());
                    userData.setPassword(passwordField.getText());
                    okClicked = true;
                    dialogStage.close();
                }
            }
            else {
                if(userData.getLogin()==null||userData.getLogin().length()==0){
                    userData.setLogin(loginField.getText());

                }
            }
        }
    }
    private boolean isInputValid() {
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
            WorkWithAlert.getInstance().showErrorAlert(" WorkWithAlert.getInstance().showErrorAlert",
                    "Исправьте недопустимые поля",errorMessage,dialogStage);
            return false;
        }
    }

}
