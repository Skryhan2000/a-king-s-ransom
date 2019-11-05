package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.user.UserData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ConcurrentHashMap;


public class SearchWindowController implements Handler{
    @FXML private TextField login;
    @FXML private TextField type;
    @FXML private Stage dialogStage;
    private UserData userData=new UserData();
    private boolean okClicked = false;
    private TableView<UserData> usersTable;
    private ObservableList<UserData> usersData;
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage, TableView<UserData> usersTable, ObservableList<UserData> usersData) {
        this.dialogStage = dialogStage;
        this.usersTable=usersTable;
        this.usersData=usersData;
    }

    @Override
    public boolean isInputValid() {
        boolean valid=false;
        if(login.getText()!=null&&login.getText().length()!=0){
            userData.setLogin(login.getText());
            valid=true;
        }
        else if(type.getText()!=null&&type.getText().length()!=0){
            userData.setType(type.getText());
            valid=true;
        }
        return valid;
    }

    @Override
    public void goBack() {
        okClicked = false;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        if(isInputValid()){
            try {
                ConcurrentHashMap<Integer, UserData> map=(ConcurrentHashMap<Integer, UserData>)CommandProvider.
                        getCommandProvider().getCommand("FIND_USERS_BY_LOGIN_AND_TYPE").execute(userData);
                if(map.size()!=0) {
                    usersData.clear();
                    usersData.addAll(map.values());
                    usersTable.refresh();
                    usersTable.setItems(usersData);
                }else {
                    WorkWithAlert.getInstance().showAlert("Поиск данных",
                            "Отчет о поиске", "Данных нет", dialogStage, "INFORMATION");
                }
                goBack();
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка", e.getExceptionMessage().toString(),
                        "Повторите попыткку позже" , dialogStage, "ERROR");
            }
        }else {
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Ошибка проверки введеных данных", "Как минимум 1 поле должно быть введено",
                    dialogStage, "ERROR");
        }
    }
}
