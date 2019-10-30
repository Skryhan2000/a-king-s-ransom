package com.loneless.server.view;

import com.loneless.server.controller.CommandProvider;
import com.loneless.server.entity.transmission.Transmission;
import com.loneless.server.entity.user.UserData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;

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
        Transmission transmission;
        Object response;
        int flag=-1;
        try {
            outObject.writeObject(flag);
            while (true) {
                transmission=(Transmission) inObject.readObject();
                if(transmission.getCommand().equalsIgnoreCase("user")) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getUserData());

                    if(response.getClass()== ConcurrentSkipListSet.class){

                    }

                    else if(response.getClass()==boolean.class){

                    }

                    else if(response.getClass()==UserData.class){

                    }
                }
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
