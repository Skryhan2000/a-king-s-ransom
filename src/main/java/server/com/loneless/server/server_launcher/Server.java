package com.loneless.server.server_launcher;

import com.loneless.server.view.ClientWorkingThread;
import com.loneless.server.view.PrimaryStage;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server extends Thread {
    private ServerSocket socket;
    private boolean isOpen;
    private static int quantity =0;
    Server(int port) {
        try {
            socket = new ServerSocket(port);
            setOpen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getQuantity() {
        return quantity;
    }

    public static void setQuantity(int quantity) {
        Server.quantity = quantity;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    ExecutorService executorService = Executors.newCachedThreadPool();

    private ArrayBlockingQueue<ClientWorkingThread> serverList = new ArrayBlockingQueue<>(10);// список всех нитей

    public int applyConnection() {

        while (isOpen) {
            try {

                ClientWorkingThread clientWorkingThread = new ClientWorkingThread(socket.accept(), serverList);// придумать выход не через IOException при команде exit
                serverList.add(clientWorkingThread);
                //              quantity++;
                System.out.println("Количество людей на сервере "+(++quantity));
                executorService.submit( clientWorkingThread);//исполняет асинхронный код в одном или нескольких потоках
            } catch(IOException e){
                e.printStackTrace();
                break;
            }


        }
        return 1;
    }

    private void setClose() {
        isOpen = false;
        try {
            serverList.clear();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


