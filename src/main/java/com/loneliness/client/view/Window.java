package com.loneliness.client.view;

import com.loneliness.client.PathManager;
import com.loneliness.client.view.fxml_controller.Reconnect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Reconnect.getInstance().reconnect();
            Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getAuthorisationFormController()));
            //Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getAdminStartWindow()));
            //Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getClientStartWindow()));
            //Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getManagerStartWindow()));
            PrimaryStage.getInstance().changeStage(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void begin(){
        launch();

    }

}
