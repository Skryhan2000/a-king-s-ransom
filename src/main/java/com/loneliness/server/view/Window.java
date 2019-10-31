package com.loneliness.server.view;

import com.loneliness.server.PathManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getStartWindow()));
            PrimaryStage.getInstance().changeStage(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void begin(){
        launch();
    }

}
