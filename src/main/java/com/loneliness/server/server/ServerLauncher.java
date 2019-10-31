package com.loneliness.server.server;

import com.loneliness.server.view.GUI;

public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8000);
        GUI gui=new GUI();
        gui.start();
        while (true){
            if(server.applyConnection()==1)
                break;
        }


    }
}
