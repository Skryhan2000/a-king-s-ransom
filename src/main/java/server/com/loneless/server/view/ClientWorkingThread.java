package com.loneless.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientWorkingThread implements Runnable{
    private ArrayBlockingQueue<ClientWorkingThread> serverList;
    private ObjectOutputStream outObject;
    private ObjectInputStream inObject;
    private Socket userSocket;
    public ClientWorkingThread(Socket userSocket, ArrayBlockingQueue serverList) {
        this.userSocket = userSocket;
        try {
            this.serverList=serverList;
            inObject=new ObjectInputStream(userSocket.getInputStream());
            outObject = new ObjectOutputStream(userSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        String nextClass;
        int flag=-1;
        try {
            outObject.writeObject(flag);
            while (true) {
                nextClass=(String) inObject.readObject();

                outObject.reset();

            }

        }  catch ( ClassNotFoundException e) {
            e.printStackTrace();
            killOneClient();
        } catch (IOException e) {
           // System.out.println("Количество людей на сервере "+(--Server.qwantity));
            killOneClient();
        }


    }
    private void killOneClient() {
        try {
            inObject.close();
            outObject.close();
            userSocket.close();
            for (ClientWorkingThread clientsList  : serverList) {
                if (clientsList.equals(this))
                    serverList.remove(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
