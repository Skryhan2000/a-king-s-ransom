package com.loneless.server;

public class PathManager {
    private static PathManager instance = new PathManager();

    public static PathManager getInstance() {
        return instance;
    }

    private PathManager() {
    }

    public String getStartWindow() {
        return "/StartWindow.fxml";
    }
}

