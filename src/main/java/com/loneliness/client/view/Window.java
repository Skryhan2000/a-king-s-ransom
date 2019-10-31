package com.loneliness.client.view;

import com.loneliness.client.PathManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PathManager.getInstance().getAuthorisationFormController()));
            PrimaryStage.getInstance().changeStage(root);
//            AuthorisationFormController controller=new AuthorisationFormController();
//            while (!controller.init()){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void begin(){
        launch();
    }

}
