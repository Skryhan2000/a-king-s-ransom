package com.loneliness.client.view.fxml_controller;

import javafx.fxml.FXML;

public interface CRUD_Controller {
    @FXML boolean goBack();
    @FXML boolean update();
    @FXML boolean addHandler();
    @FXML boolean deleteHandler();
    @FXML boolean changeHandler();
}
