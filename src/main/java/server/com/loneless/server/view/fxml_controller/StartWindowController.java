package com.loneless.server.view.fxml_controller;

import com.loneless.server.view.QuantityBoard;
import javafx.fxml.FXML;

import java.util.Observable;
import java.util.Observer;

public class StartWindowController {
    @FXML private Integer quantity=0;
    private class QuantityPeopleOnServer implements Observer{
        @Override
        public void update(Observable o, Object arg) {
            quantity=(Integer)arg;

            System.out.println("Message board changed: " + arg);
        }
    }
    @FXML
    private void initialize(){
        QuantityPeopleOnServer quantityPeopleOnServer=new QuantityPeopleOnServer();
        QuantityBoard.getInstance().addObserver(quantityPeopleOnServer);
    }


}
