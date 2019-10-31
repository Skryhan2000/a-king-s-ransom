package com.loneliness.client.view.fxml_controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class WorkWithAlert {
    private static final WorkWithAlert instance = new WorkWithAlert();

    private WorkWithAlert() {
    }

    public static WorkWithAlert getInstance() {
        return instance;
    }

    public void showAlert(String title, String headerText, String contentText, Stage dialogStage, String type) {
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
