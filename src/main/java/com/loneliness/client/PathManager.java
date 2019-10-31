package com.loneliness.client;

public class PathManager {
    private static PathManager instance = new PathManager();

    public static PathManager getInstance() {
        return instance;
    }

    private PathManager() {
    }

    public String getAuthorisationFormController() {
        return "/AuthorisationForm.fxml";
    }
}
