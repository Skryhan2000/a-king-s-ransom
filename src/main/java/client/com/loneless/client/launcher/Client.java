package com.loneless.client.launcher;

import java.io.*;
import java.net.Socket;

import static javafx.application.Application.launch;

class Client extends Thread {
    private Socket socket;
    private String ipAddress;
    private int port ;
    private static ObjectOutputStream outObject;
    private static ObjectInputStream inObject;
    Client(String ipAdr, int port) {
        this.ipAddress = ipAdr;
        this.port = port;
        try {
            this.socket = new Socket(ipAdr, port);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ObjectOutputStream getOutObject() {
        return outObject;
    }

    public static void setOutObject(ObjectOutputStream outObject) {
        Client.outObject = outObject;
    }

    public static ObjectInputStream getInObject() {
        return inObject;
    }

    public static void setInObject(ObjectInputStream inObject) {
        Client.inObject = inObject;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void run(){

        try {
//            OutStream.getInstanse().setSocket(socket);
//            InStream.getInstanse().setSocket(socket);
            outObject = new ObjectOutputStream(socket.getOutputStream());
            inObject=new ObjectInputStream(socket.getInputStream());

//            outObject = OutStream.getInstanse().getOutObject();
//            inObject=InStream.getInstanse().getInObject();
//            int flag=(int)inObject.readObject();
//            SequenceControl sequenceControl=new  SequenceControl();
//            sequenceControl.begin(flag);

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }



    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                inObject.close();
                outObject.close();
            }
        } catch (IOException ignored) {}
    }

}