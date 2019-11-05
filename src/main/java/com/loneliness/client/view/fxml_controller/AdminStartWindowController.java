package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.client.view.ViewException;
import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


public class AdminStartWindowController implements CRUD_Controller{
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
    @FXML
    private Stage dialogStage;

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
    @Override
    public boolean update() {
        try {
            usersData.clear();
            Transmission transmission = new Transmission();
            transmission.setCommand("RECEIVE_ALL_USERS");
            setAndGetUsersData((ConcurrentHashMap<Integer, UserData>) CommandProvider.
                    getCommandProvider().getCommand("RECEIVE_ALL_USERS")
                    .execute(transmission));
            usersTable.refresh();
            usersTable.setItems(usersData);
            return true;
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
return false;
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
    @FXML private void searchHandler(){}

    @Override
    public boolean addHandler() {
        Stage dialogStage = null;
        try {
            dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                    getChangeUSerData(), "Добавления пользователя");
            ChangeUserDataController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
            controller.setData(new UserData());
            controller.setDialogStage(dialogStage, "create");
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (ViewException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteHandler(){
        int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                UserData userData=new UserData();
                userData.setId(usersData.get(selectedIndex).getId());
                if((Boolean) CommandProvider.getCommandProvider().getCommand("DELETE_USER").execute(userData)) {
                    usersTable.getItems().remove(selectedIndex);
                    WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                            "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                }
                else {
                    WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                            "Ошибка", "Попробуйте повторить действие позже",
                            this.dialogStage, "ERROR");
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                        "Неизвестная ошибка", "Попробуйте повторить действие позже",
                        this.dialogStage, "ERROR");
            }

        } else {
            // Ничего не выбрано.
            WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                    "Удаление невозможно", "Выберите пользователя для удаления",
                    this.dialogStage, "ERROR");
        }
        return false;
    }
    @Override
    public boolean changeHandler(){
        UserData userData=new UserData();
        userData=getSelectedModel(userData);
        if(userData!=null) {
            Stage dialogStage = null;
            try {
                dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                        getChangeUSerData(), "Добавления пользователя");
                ChangeUserDataController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                controller.setDialogStage(dialogStage, "update");
                controller.setData(userData);
                dialogStage.showAndWait();
                return controller.isOkClicked();
            } catch (ViewException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean goBack(){
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getRegistrationForm())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                    "Нарушение целостности программы", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    private UserData getSelectedModel(UserData selectedUser){
       selectedUser=usersTable.getSelectionModel().getSelectedItem();
        if(selectedUser==null){
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return selectedUser;
        }
        else{
            return selectedUser;
        }
    }
}
