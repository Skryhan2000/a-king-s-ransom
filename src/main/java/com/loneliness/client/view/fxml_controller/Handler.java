package com.loneliness.client.view.fxml_controller;

import javafx.fxml.FXML;

public interface Handler {
    @FXML boolean isInputValid();
    @FXML void goBack();
    @FXML void finishWork();
}
