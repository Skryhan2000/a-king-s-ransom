package com.loneliness.server.view;

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
