package com.loneliness.server.server;

import com.loneliness.server.view.GUI;

//mvn exec:java -Dexec.mainClass="com.loneliness.server.server.ServerLauncher" -Dexec.args"8000"
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
