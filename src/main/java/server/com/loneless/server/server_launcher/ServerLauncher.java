package com.loneless.server.server_launcher;

import com.loneless.server.view.GUI;
import com.loneless.server.view.Window;
import javafx.stage.Stage;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8000);
        GUI gui=new GUI();
        gui.run();
        while (true){
            if(server.applyConnection()==1)
                break;
        }

    }
}
