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
    private static int capacity=3;
    private static AtomicInteger quantity=new AtomicInteger();
    private static ExecutorService executorService = Executors.newCachedThreadPool();//кэширующий пул потоков, который создает потоки по мере необходимости, но переиспользует неактивные потоки (и подчищает потоки, которые были неактивные некоторое время)
    private static ArrayBlockingQueue<ClientWorkingThread> serverList = new ArrayBlockingQueue<>(capacity);// список всех нитей

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
                if(quantity.get()<capacity) {
                ClientWorkingThread clientWorkingThread = new ClientWorkingThread(socket.accept(), serverList);
                    quantity.incrementAndGet();
                    serverList.add(clientWorkingThread);
                    executorService.submit(clientWorkingThread);//исполняет асинхронный код в одном или нескольких потоках
                }
            }
            catch (java.lang.IllegalStateException e){

            }
            catch(IOException e){
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


