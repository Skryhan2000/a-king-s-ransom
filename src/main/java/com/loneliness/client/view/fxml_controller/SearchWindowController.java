package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.user.UserData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ConcurrentHashMap;


public class SearchWindowController implements Handler{
    @FXML private Label name;
    @FXML private Label name1;
    @FXML private TextField textField;
    @FXML private TextField textField1;
    @FXML private Stage dialogStage;
    private ProviderData providerData=new ProviderData();
    private UserData userData=new UserData();
    private boolean okClicked = false;
    private TableView<UserData> usersTable;
    private ObservableList<UserData> usersData;
    private String type;
    private TableView<ProviderData> providersTable;
    private ObservableList<ProviderData> providersData;
    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStageUser(Stage dialogStage, TableView<UserData> usersTable, ObservableList<UserData> usersData, String type) {
        this.dialogStage = dialogStage;
        this.usersTable = usersTable;
        this.usersData = usersData;
        this.type = type;
        name.setText("Логин");
        name1.setText("Права доступа");

    }

    public void setDialogStageProviders(Stage dialogStage, TableView<ProviderData> providersTable, ObservableList<ProviderData> providersData, String type) {
        this.dialogStage = dialogStage;
        this.providersTable=providersTable;
        this.providersData=providersData;
        this.type=type;
        name.setText("Местоположение");
        name1.setText("Рейтинг");

    }

    @Override
    public boolean isInputValid() {
        boolean valid=false;
        if(textField.getText()!=null&& textField.getText().length()!=0){
            valid=true;
        }
         if(textField1.getText()!=null&& textField1.getText().length()!=0){
            try {
                if(Integer.parseInt(textField1.getText())>=0) {
                    valid = true;
                }else {
                    WorkWithAlert.getInstance().showAlert("Неверный ввод",
                            "Ошибка проверки введеных данных", "В воле рейтинг должно быть не отрицательное число",
                            dialogStage, "ERROR");
                }
            }
            catch (NumberFormatException e){
                valid=false;
                WorkWithAlert.getInstance().showAlert("Неверный ввод",
                        "Ошибка проверки введеных данных", "В воле рейтинг должно быть число",
                        dialogStage, "ERROR");
            }
        }
        return valid;
    }
    private void setData(UserData userData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            userData.setLogin(textField.getText());
        }
        if(textField1.getText()!=null&& textField1.getText().length()!=0) {
            userData.setType(textField1.getText());
        }
    }
    private void setData(ProviderData providerData){
        if(textField.getText()!=null&& textField.getText().length()!=0){
            providerData.setLocation(textField.getText());
        }
        if(textField1.getText()!=null&& textField1.getText().length()!=0) {
            providerData.setRating(Integer.parseInt(textField1.getText()));
        }
        else  providerData.setRating(-1);
    }

    @Override
    public void goBack() {
        okClicked = false;
        dialogStage.close();
    }

    @Override
    public void finishWork() {
        if(isInputValid()){

            boolean err=false;
            try {
                switch (type) {
                    case "users":
                        setData(userData);
                        ConcurrentHashMap<Integer, UserData> userMap = (ConcurrentHashMap<Integer, UserData>) CommandProvider.
                            getCommandProvider().getCommand("FIND_USERS_BY_LOGIN_AND_TYPE").execute(userData);
                     if(userMap.size()!=0) {
                        usersData.clear();
                        usersData.addAll(userMap.values());
                        usersTable.refresh();
                        usersTable.setItems(usersData);
                    }
                     else err=true;
                     break;
                    case "providers":
                        setData(providerData);
                        ConcurrentHashMap<Integer, ProviderData> providerMap = (ConcurrentHashMap<Integer, ProviderData>) CommandProvider.
                                getCommandProvider().getCommand("FIND_PROVIDER_BY_LOCATION_AND_RATING").execute(providerData);
                        if(providerMap.size()!=0) {
                            providersData.clear();
                            providersData.addAll(providerMap.values());
                            providersTable.refresh();
                            providersTable.setItems(providersData);
                        }
                }

                if(err){
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
