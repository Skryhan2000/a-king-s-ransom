package com.loneliness.client.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryStage {
    private volatile static PrimaryStage instance;
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    private PrimaryStage(){
        primaryStage=new Stage();
    }
    public static PrimaryStage getInstance(){
        if(instance ==null ){
            synchronized (PrimaryStage.class) {
                if(instance ==null )
                    instance = new PrimaryStage();
            }
        }
        return instance;
    }
    public void changeStage(Scene scene, Parent root){
        this.scene=scene;
        this.root=root;
        primaryStage.setTitle("a-king-s-ransom");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeStage(Parent root){
        this.root=root;
        scene = new Scene(root, 600, 400);
        primaryStage.setTitle("a-king-s-ransom");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
