package com.loneless.server.view;

import com.loneless.server.PathManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class GUI extends Thread {
    @Override
    public void run() {
        Window window = new Window();
        try {
            window.begin();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
