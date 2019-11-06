package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.PathManager;
import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.client.view.PrimaryStage;
import com.loneliness.client.view.ViewException;
import com.loneliness.entity.ProviderData;
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


public class AdminStartWindowController implements CRUD_Controller {
    @FXML
    private Stage dialogStage;
    private int[] indexOfCurrentValue = {0, 20};
    private String dataType;

    @FXML
    private TableView<UserData> usersTable;
    @FXML
    private TableColumn<UserData, String> usersLoginColumn;
    @FXML
    private TableColumn<UserData, String> usersTypeColumn;
    @FXML
    private Text userLogin;
    @FXML
    private Text userPassword;
    @FXML
    private Text userQuestion;
    @FXML
    private Text userAnswer;
    @FXML
    private Text type;
    private ObservableList<UserData> usersData = FXCollections.observableArrayList();



    @FXML
    private TableView<ProviderData> providerTable;
    @FXML
    private TableColumn<ProviderData, String> providerLocationColumn;
    @FXML
    private TableColumn<ProviderData, Integer> providerRatingColumn;

    private ObservableList<ProviderData> providersData = FXCollections.observableArrayList();
    @FXML
    private Text providerName;
    @FXML
    private Text providerLocation;
    @FXML
    private Text providerRating;




    @FXML
    private void setDataTypeUsers() {
        if (dataType != null) {
            if (!dataType.equals("users")) {
                dataType = "users";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeProviders() {
        if(dataType!=null) {
            if (!dataType.equals("providers")) {
                dataType = "providers";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeProductInStock() {
        if(dataType!=null) {
            if (!dataType.equals("product_in_stock")) {
                dataType = "product_in_stock";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void setDataTypeOrders() {
        if(dataType!=null) {
            if (!dataType.equals("оrders")) {
                dataType = "оrders";
                indexOfCurrentValue[0] = 0;
                indexOfCurrentValue[1] = 20;
                initialize();
            }
        }
    }

    @FXML
    private void addTwentyNode() {
        indexOfCurrentValue[0] += 20;
        indexOfCurrentValue[1] += 20;
        update();
    }

    @FXML
    private void removeTwentyNode() {
        indexOfCurrentValue[0] -= 20;
        indexOfCurrentValue[1] -= 20;
        if (indexOfCurrentValue[0] < 0) {
            indexOfCurrentValue[0] = 0;
            indexOfCurrentValue[1] = 20;
        }
        update();
    }

    public ObservableList<UserData> setAndGetUsersData(ConcurrentHashMap<Integer, UserData> map) {
        usersData.addAll(map.values());
        return usersData;
    }
    public ObservableList<ProviderData> setAndGetProvidersData(ConcurrentHashMap<Integer, ProviderData> map) {
        providersData.addAll(map.values());
        return providersData;
    }

    private void fillText(UserData userData) {
        if (userData == null) {
            userLogin.setText("");
            userPassword.setText("");
            userQuestion.setText("");
            userAnswer.setText("");
            type.setText("");
        } else {
            userLogin.setText(userData.getLogin());
            userPassword.setText(userData.getPassword());
            userQuestion.setText(userData.getSecretQuestion());
            userAnswer.setText(userData.getSecretAnswer());
            type.setText(userData.getType());
        }
    }
        private void fillText(ProviderData providerData) {
            if (providerData == null) {
                providerName.setText("");
                providerLocation.setText("");
                providerRating.setText("");
            } else {
                providerName.setText(providerData.getName());
                providerLocation.setText(providerData.getLocation());
                providerRating.setText(String.valueOf(providerData.getRating()));
            }

        }


    @Override
    public boolean update() {
        try {
            Transmission transmission = new Transmission();
            transmission.setFirstIndex(indexOfCurrentValue[0]);
            transmission.setLastIndex(indexOfCurrentValue[1]);
            if(dataType==null){
                dataType="users";
            }
            switch (dataType) {
                case "users":
                    usersData.clear();
                    transmission.setCommand("RECEIVE_ALL_USERS_IN_LIMIT");
                    setAndGetUsersData((ConcurrentHashMap<Integer, UserData>) CommandProvider.
                            getCommandProvider().getCommand("RECEIVE_ALL_USERS_IN_LIMIT")
                            .execute(transmission));
                    usersTable.refresh();
                    usersTable.setItems(usersData);
                    return true;
                case "providers":
                    providersData.clear();
                    transmission.setCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT");
                    setAndGetProvidersData((ConcurrentHashMap<Integer, ProviderData>)CommandProvider.
                            getCommandProvider().getCommand("RECEIVE_ALL_PROVIDERS_IN_LIMIT")
                            .execute(transmission));
                    providerTable.refresh();
                    providerTable.setItems(providersData);
                    return true;
            }

        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    @FXML
    private void initialize() {
        if(dataType==null){
            dataType="users";
        }
        switch (dataType) {
            case "users":
                usersTable.getSelectionModel().selectedItemProperty().addListener((
                        (observableValue, userData, newUserData) -> fillText(newUserData)));

                usersLoginColumn.setCellValueFactory(userDataStringCellDataFeatures -> userDataStringCellDataFeatures.
                        getValue().loginPropertyProperty());
                usersTypeColumn.setCellValueFactory(userDataStringCellDataFeatures -> userDataStringCellDataFeatures.
                        getValue().typePropertyProperty());
                UserData userData = null;
                fillText(userData);
                break;
            case "providers":
                providerTable.getSelectionModel().selectedItemProperty().addListener((
                        (observableValue, providerData, newProviderData) -> fillText(newProviderData)));

                providerLocationColumn.setCellValueFactory(providerDataStringCellDataFeatures ->
                        providerDataStringCellDataFeatures.getValue().locationStringProperty());
                providerRatingColumn.setCellValueFactory(providerDataIntegerCellDataFeatures ->
                        providerDataIntegerCellDataFeatures.getValue().ratingIntegerProperty().asObject());
                ProviderData providerData = null;
                fillText(providerData);
                break;
        }
        update();
    }

    @FXML
    private boolean searchHandler() {
        Stage dialogStage = null;
        try {
            switch (dataType) {
                case "users":
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getSearchUserData(), "Поиск пользователя");
                    SearchWindowController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    controller.setDialogStage(dialogStage, usersTable, usersData);
                    dialogStage.showAndWait();
                    return controller.isOkClicked();
            }
        } catch (ViewException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addHandler() {
        Stage dialogStage = null;
        try {
            switch (dataType) {
                case "users":
                    dialogStage = WorkWithFXMLLoader.getInstance().createStage(PathManager.getInstance().
                            getChangeUSerData(), "Добавления пользователя");
                    ChangeUserDataController controller = WorkWithFXMLLoader.getInstance().getLoader().getController();
                    controller.setData(new UserData());
                    controller.setDialogStage(dialogStage, "create");
                    dialogStage.showAndWait();
                    return controller.isOkClicked();
            }
        } catch (ViewException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteHandler() {
        int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            try {
                switch (dataType) {
                    case "users":
                        UserData userData = new UserData();
                        userData.setId(usersData.get(selectedIndex).getId());
                        if ((Boolean) CommandProvider.getCommandProvider().getCommand("DELETE_USER").execute(userData)) {
                            usersTable.getItems().remove(selectedIndex);
                            WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                                    "Успех", "Данные сохранены", dialogStage, "INFORMATION");
                        } else {
                            WorkWithAlert.getInstance().showAlert("Удаление пользователя",
                                    "Ошибка", "Попробуйте повторить действие позже",
                                    this.dialogStage, "ERROR");
                        }
                        break;
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
    public boolean changeHandler() {
        switch (dataType) {
            case "users":
                UserData userData = new UserData();
                userData = getSelectedModel(userData);
                if (userData != null) {
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
        return false;
    }

    @Override
    public boolean goBack() {
        try {
            PrimaryStage.getInstance().changeStage(FXMLLoader.load(getClass().getResource(PathManager.
                    getInstance().getAuthorisationFormController())));
        } catch (IOException e) {
            WorkWithAlert.getInstance().showAlert("Неизвестная ошибка",
                    "Нарушение целостности программы", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
        return false;
    }

    private UserData getSelectedModel(UserData selectedUser) {
        selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            WorkWithAlert.getInstance().showAlert("Отсутствие выбора",
                    "Данные не выбраны", "Пожалуйста выберите данные в таблице.",
                    this.dialogStage, "ERROR");
            return selectedUser;
        } else {
            return selectedUser;
        }
    }
}
