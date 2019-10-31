package com.loneliness.server.view;

import com.loneliness.server.controller.CommandProvider;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class StartWindowController {
    @FXML static private Label quantityLabel=new Label();
    @FXML private TextArea console=new TextArea();
    @FXML private static int quantity=0;
    @FXML static synchronized public void updateQuantity(int val){
        quantity+=val;
        quantityLabel.setText(Integer.toString(quantity));
        System.out.println(quantity);

    }



}
