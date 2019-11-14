package com.loneliness.client.view.fxml_controller;

import com.loneliness.entity.ProviderData;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

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
    public void showAlert(String title, String headerText, Set<ConstraintViolation<Object>> errors,
                          Stage dialogStage, String type){
        Alert alert = new Alert(Alert.AlertType.valueOf(type));
        StringBuilder errMessage=new StringBuilder();
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        for (ConstraintViolation<Object> violation : errors) {
           errMessage.append(violation.getMessage());
        }

        alert.setContentText(errMessage.toString());
        alert.showAndWait();
    }
    public boolean showAnswer(String answer, Stage dialogStage,String title){
        if(answer.startsWith("ERROR")){
            answer=answer.replace("ERROR","");
            showAlert(title, title+"ошибка ", answer, dialogStage, "ERROR");
            return false;
        }
        else {
            answer=answer.replace("ERROR","");
           showAlert(title, title+" успех", answer, dialogStage, "INFORMATION");
            return true;
        }
    }
}
