package com.loneliness.server.view;

import com.loneliness.entity.transmission.Transmission;
import com.loneliness.server.controller.CommandProvider;
import com.loneliness.server.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;


public class ClientWorkingThread implements Runnable{
    private static final Logger logger = LogManager.getLogger();
    private ArrayBlockingQueue<ClientWorkingThread> serverList;
    private ObjectOutputStream outObject;
    private ObjectInputStream inObject;
    private Socket userSocket;
    public ClientWorkingThread(Socket userSocket, ArrayBlockingQueue<ClientWorkingThread> serverList) {
        this.userSocket = userSocket;
        try {
                this.serverList = serverList;
                inObject = new ObjectInputStream(userSocket.getInputStream());
                outObject = new ObjectOutputStream(userSocket.getOutputStream());
        } catch (IOException e) {
            logger.catching(e);
            Server.getQuantity().decrementAndGet();
        }
    }
    @Override
    public void run() {
        Transmission transmission;
        Object response;
        try {
            while (true) {
                transmission = (Transmission) inObject.readObject();
                if (transmission.getUserData() != null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getUserData());

                } else if (transmission.getProviderData() != null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getProviderData());

                } else if (transmission.getOrderData() != null) {

                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getOrderData());
                } else if (transmission.getCustomerData() != null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getCustomerData());
                } else if (transmission.getProductInStock() != null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getProductInStock());
                } else if (transmission.getProductData() != null) {
                    response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                            execute(transmission.getProductData());
                } else {
                    if (transmission.getCommand().equals("RECEIVE_PRODUCT_GOODS")) {
                        response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                                execute(transmission.getIntegerSet());
                    } else {
                        response = CommandProvider.getCommandProvider().getCommand(transmission.getCommand()).
                                execute(transmission);
                    }
                }
                outObject.writeObject(response);
                outObject.reset();
            }

        } catch (ClassNotFoundException e) {
            logger.catching(e);
           // e.printStackTrace();
        } catch (IOException e) {
            killOneClient();
            logger.catching(e);
           // e.printStackTrace();
        }
    }

    private void killOneClient() {
        try {
            inObject.close();
            outObject.close();
            userSocket.close();
            Server.getQuantity().decrementAndGet();
            for (ClientWorkingThread clientsList  : serverList) {
                if (clientsList.equals(this))
                    serverList.remove(this);
            }
        } catch (IOException e) {
            logger.catching(e);
        }

    }
}
