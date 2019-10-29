package com.loneless.client.launcher;

public class ClientLauncher {
    public static void main(String[] args) {

        String ipAddr = "localhost";
        int port = 8000;
        Client client = new Client(ipAddr,port);
        client.run();


    }
}
