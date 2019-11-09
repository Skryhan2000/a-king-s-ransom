package com.loneliness.server.view;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.entity.user.UserData;
import com.loneliness.server.controller.CommandProvider;


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
    public ClientWorkingThread(Socket userSocket, ArrayBlockingQueue<ClientWorkingThread> serverList) {
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
        Object response = null;
        try {
            while (true) {
                transmission=(Transmission) inObject.readObject();
                if(transmission.getUserData()!=null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getUserData());

                }else if(transmission.getProviderData()!=null){
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getProviderData());

                }else if(transmission.getOrderData()!=null){
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getOrderData());
                }
                else if(transmission.getCustomerData()!=null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getCustomerData());
                }else { response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                        execute(transmission);}
                outObject.writeObject(response);
                outObject.reset();
            }

        }  catch ( ClassNotFoundException e) {
            e.printStackTrace();
            killOneClient();
        } catch (IOException e) {
           // System.out.println("Количество людей на сервере "+(--Server.qwantity));
           // synchronized (StartWindowController.class){StartWindowController.updateQuantity(-1);}
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
