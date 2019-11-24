package com.loneliness.server.server;

import com.loneliness.server.view.GUI;


public class ServerLauncher {
    public static void main(String[] args)  {
        Server server = new Server(Integer.parseInt(args[0]));
        GUI gui=new GUI();
        gui.start();
        while (true){
            if(server.applyConnection()==1)
                break;
        }


    }
}
