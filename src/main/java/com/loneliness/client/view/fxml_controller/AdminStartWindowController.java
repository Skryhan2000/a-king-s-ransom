package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.concurrent.ConcurrentHashMap;


public class AdminStartWindowController {
    private int[] indexOfCurrentValue={0,20};
    @FXML
    private TableView<UserData> usersTable;
    @FXML
    private TableColumn<UserData,String> loginColumn;
    @FXML
    private TableColumn<UserData,String> typeColumn;
    @FXML
    private Text login;
    @FXML
    private Text password;
    @FXML
    private Text question;
    @FXML
    private Text answer;
    @FXML
    private Text type;

    private ObservableList<UserData> usersData = FXCollections.observableArrayList();

    public ObservableList<UserData> setAndGetUsersData(ConcurrentHashMap<Integer,UserData> map) {
      usersData.addAll(map.values());
      return usersData;
    }
    private void fillText(UserData userData){
        if(userData==null){
            login.setText("");
            password.setText("");
            question.setText("");
            answer.setText("");
            type.setText("");
        }
        else {
            login.setText(userData.getLogin());
            password.setText(userData.getPassword());
            question.setText(userData.getSecretQuestion());
            answer.setText(userData.getSecretAnswer());
            type.setText(userData.getType());
        }
    }
    @FXML private void update() {
        try {
            usersData.clear();
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_USERS");
            setAndGetUsersData((ConcurrentHashMap<Integer, UserData>) CommandProvider.
                    getCommandProvider().getCommand("RECEIVE_ALL_USERS")
                    .execute(transmission));
            usersTable.refresh();
            usersTable.setItems(usersData);

        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }

    @FXML private void initialize(){
        loginColumn.setCellValueFactory(userDataStringCellDataFeatures -> userDataStringCellDataFeatures.
                getValue().loginPropertyProperty());
        typeColumn.setCellValueFactory(userDataStringCellDataFeatures->userDataStringCellDataFeatures.
                getValue().typePropertyProperty());
        fillText(null);
        usersTable.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, userData1, newUserData) -> fillText(newUserData)));
        update();
    }
}
