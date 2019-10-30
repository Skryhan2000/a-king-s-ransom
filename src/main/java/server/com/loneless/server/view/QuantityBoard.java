package com.loneless.server.view;

import com.loneless.server.view.fxml_controller.StartWindowController;

import java.util.Observable;
import java.util.Observer;

public class QuantityBoard extends Observable {
    private static final QuantityBoard instance=new QuantityBoard();
    QuantityBoard(){}

    public static QuantityBoard getInstance() {
        return instance;
    }

    public void changeQuantity(int quantity)
    {
        setChanged();
        notifyObservers(quantity);
    }

    public static void main(String[] args)
    {
        QuantityBoard board = new QuantityBoard();

        board.changeQuantity(5);
    }

}
