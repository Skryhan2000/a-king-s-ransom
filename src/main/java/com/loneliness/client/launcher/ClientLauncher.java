package com.loneliness.client.launcher;

public class ClientLauncher {
    public static void main(String[] args) {

        String ipAddress = "localhost";
        int port = 8000;
        Client client = new Client(ipAddress,port);
        client.run();


    }
}
