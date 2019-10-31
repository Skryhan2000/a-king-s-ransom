package com.loneliness.client.view.fxml_controller;

import com.loneliness.client.view.PrimaryStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkWithFXMLLoader {
    private static final WorkWithFXMLLoader instance=new WorkWithFXMLLoader();
    private volatile static  FXMLLoader loader = new FXMLLoader();
    private WorkWithFXMLLoader(){}

    public static WorkWithFXMLLoader getInstance() {
        return instance;
    }

    public Stage createStage(String resource,String title) throws IOException {
        loader.setLocation(getClass().getResource(resource));
        BorderPane page = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(PrimaryStage.getInstance().getPrimaryStage());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        return dialogStage;
    }

    public FXMLLoader getLoader() {
        return loader;
    }
    public void setLoader(String resource){
        loader.setLocation(getClass().getResource(resource));
    }
}
