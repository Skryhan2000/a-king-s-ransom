package com.loneliness.server.server;

import com.loneliness.server.view.GUI;


public class ServerLauncher {
    public static void main(String[] args)  {
        int port=8000;
        if (args.length!=0){
            port=Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        GUI gui=new GUI();
        gui.start();
        while (true){
            if(server.applyConnection()==1)
                break;
        }


    }
}
