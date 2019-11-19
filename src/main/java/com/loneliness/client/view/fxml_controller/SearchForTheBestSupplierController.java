package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.controller.CommandProvider;
import com.loneliness.client.controller.ControllerException;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.transmission.Transmission;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.ConcurrentHashMap;

public class SearchForTheBestSupplierController {
    private boolean okClicked=false;
    @FXML
    private Stage dialogStage;
    @FXML
    private Hyperlink providerEmailLabel;
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
    private ToggleGroup balanceGroup = new ToggleGroup();
    @FXML private TextField locationToFind;
    @FXML private TextField ratingToFind;
    @FXML private RadioButton locationMoreImportant;
    @FXML private RadioButton ratingMoreImportant;
    ProviderData providerData=new ProviderData();
    public ObservableList<ProviderData> setAndGetProvidersData(ConcurrentHashMap<Integer, ProviderData> map) {
        providersData.addAll(map.values());
        return providersData;
    }

    private void fillText(ProviderData providerData) {
        if (providerData == null) {
            providerName.setText("");
            providerLocation.setText("");
            providerRating.setText("");
            providerEmailLabel.setText("");
        } else {
            providerName.setText(providerData.getName());
            providerLocation.setText(providerData.getLocation());
            providerRating.setText(String.valueOf(providerData.getRating()));
            providerEmailLabel.setText(providerData.getEmail());
        }

    }

    @FXML
    private void update() {
        locationToFind.setText("");
        ratingToFind.setText("");
        Transmission transmission = new Transmission();
        providersData.clear();
        transmission.setCommand("RECEIVE_ALL_PROVIDERS");
        try {
            setAndGetProvidersData((ConcurrentHashMap<Integer, ProviderData>) CommandProvider.
                    getCommandProvider().getCommand("RECEIVE_ALL_PROVIDERS")
                    .execute(transmission));
            providerTable.refresh();
            providerTable.setItems(providersData);
        } catch (ControllerException e) {
            WorkWithAlert.getInstance().showAlert("Ошибка обновленя",
                    "Неизвестная ошибка", "Попробуйте повторить действие позже",
                    this.dialogStage, "ERROR");
        }
    }

    @FXML
    private void initialize() {
        locationMoreImportant.setToggleGroup(balanceGroup);
        ratingMoreImportant.setToggleGroup(balanceGroup);
        providerTable.getSelectionModel().selectedItemProperty().addListener((
                (observableValue, providerData, newProviderData) -> fillText(newProviderData)));

        providerLocationColumn.setCellValueFactory(providerDataStringCellDataFeatures ->
                providerDataStringCellDataFeatures.getValue().locationStringProperty());
        providerRatingColumn.setCellValueFactory(providerDataIntegerCellDataFeatures ->
                providerDataIntegerCellDataFeatures.getValue().ratingIntegerProperty().asObject());
        ProviderData providerData = null;
        fillText(providerData);
        update();
    }
    @FXML
    public void goBack() {
        okClicked = true;
        dialogStage.close();
    }
    @FXML private void search() {
        boolean noData = true;
        if (isValid()) {
            try {
                ConcurrentHashMap<Integer, ProviderData> providerMap = null;

                providerMap = (ConcurrentHashMap<Integer, ProviderData>) CommandProvider.
                        getCommandProvider().getCommand("FIND_PROVIDER_BY_LOCATION_RATING_AND_VALUE").execute(providerData);

                if (providerMap.size() != 0) {
                    providersData.clear();
                    providersData.addAll(providerMap.values());
                    providerTable.refresh();
                    providerTable.setItems(providersData);
                    noData = false;
                }

                if (noData) {
                    WorkWithAlert.getInstance().showAlert("Поиск данных",
                            "Отчет о поиске", "Данных нет", dialogStage, "INFORMATION");
                }
            } catch (ControllerException e) {
                WorkWithAlert.getInstance().showAlert("Ошибка", e.getExceptionMessage().toString(),
                        "Повторите попыткку позже" , dialogStage, "ERROR");
            }
        }

    }
    private boolean isValid(){
        boolean valid=false;
        providerData.setLocation(locationToFind.getText());
        if(providerData.getLocation()!=null){
            valid=true;
        }
        try {
            if (ratingToFind.getText() != null && ratingToFind.getText().length() != 0) {
                providerData.setRating(Integer.parseInt(ratingToFind.getText()));
                if (providerData.getRating() >= 0) {
                    valid = true;
                } else {
                    WorkWithAlert.getInstance().showAlert("Неверный ввод",
                            "Ошибка проверки введеных данных", "В поле количество должно быть не отрицательное число",
                            dialogStage, "ERROR");
                }
            }
            } catch(NumberFormatException e){
                valid = false;
                WorkWithAlert.getInstance().showAlert("Неверный ввод",
                        "Ошибка проверки введеных данных", "В поле количество должно быть число",
                        dialogStage, "ERROR");
            }
        if(valid){
            if(locationMoreImportant.isSelected()){
                providerData.setBalance("location");
            }
            else if (ratingMoreImportant.isSelected()){
                providerData.setBalance("rating");
            }
            else  providerData.setBalance(" ");
            return true;
        }
        else {
            WorkWithAlert.getInstance().showAlert("Неверный ввод",
                    "Ошибка проверки введеных данных", "Для поиска должен быть задан хотя бы 1 параметр",
                    dialogStage, "ERROR");
        }
        return false;
    }
}
