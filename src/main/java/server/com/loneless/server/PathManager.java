package com.loneless.server;

public class PathManager {
    private static PathManager ourInstance = new PathManager();

    public static PathManager getInstance() {
        return ourInstance;
    }

    private PathManager() {
    }

}
