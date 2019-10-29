package com.loneless.client;

public class PathManager {
    private static PathManager instance = new PathManager();

    public static PathManager getInstance() {
        return instance;
    }

    private PathManager() {
    }
    private String authorisationFormController="com.loneless.client.view.fxml_controller.AuthorisationFormController";

    public String getAuthorisationFormController() {
        return authorisationFormController;
    }
}
