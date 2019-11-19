package com.loneliness.server.server;

import com.loneliness.server.view.ClientWorkingThread;
import com.loneliness.server.view.StartWindowController;
import javafx.application.Platform;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
    private static ServerSocket socket;
    private static boolean isOpen;
    private static AtomicInteger quantity=new AtomicInteger();
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private static ArrayBlockingQueue<ClientWorkingThread> serverList = new ArrayBlockingQueue<>(10);// список всех нитей

    Server(int port) {
        try {
            socket = new ServerSocket(port);
            setOpen(true);
            quantity.set(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setOpen(boolean open) {
        isOpen = open;
    }



    public int applyConnection() {
        while (isOpen) {
            try {

                ClientWorkingThread clientWorkingThread = new ClientWorkingThread(socket.accept(), serverList);// придумать выход не через IOException при команде exit
                serverList.add(clientWorkingThread);
                quantity.incrementAndGet();
                //System.out.println("Количество людей на сервере "+(++quantity));
               // Platform.runLater(()-> StartWindowController.updateQuantity(+1));
             //   StartWindowController.updateQuantity(+1);
                executorService.submit(clientWorkingThread);//исполняет асинхронный код в одном или нескольких потоках
            } catch(IOException e){
                quantity.decrementAndGet();
                e.printStackTrace();
                break;
            }


        }
        return 1;
    }

    public static void close() {
        try {
            isOpen = false;
            socket.close();
            serverList.clear();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AtomicInteger getQuantity() {
        return quantity;
    }
}


