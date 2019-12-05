package com.loneliness.server.server;

import com.loneliness.server.view.ClientWorkingThread;
import com.loneliness.server.view.StartWindowController;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
    private static final Logger logger = LogManager.getLogger();
    private static ServerSocket socket;
    private static boolean isOpen;
    private static int capacity=50;
    private static AtomicInteger quantity=new AtomicInteger();
    private static ExecutorService executorService = Executors.newCachedThreadPool();//кэширующий пул потоков, который создает потоки по мере необходимости, но переиспользует неактивные потоки (и подчищает потоки, которые были неактивные некоторое время)
    private static ArrayBlockingQueue<ClientWorkingThread> serverList = new ArrayBlockingQueue<>(capacity);// список всех нитей

    Server(int port) {
        try {
            socket = new ServerSocket(port);
            setOpen(true);
            logger.info("Запуск сервера");
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
                    logger.info("Новое подключение");
                    ClientWorkingThread clientWorkingThread = new ClientWorkingThread(socket.accept(), serverList);
                    quantity.incrementAndGet();
                    serverList.add(clientWorkingThread);
                    executorService.submit(clientWorkingThread);//исполняет асинхронный код в одном или нескольких потоках
                }
            }
            catch (java.lang.IllegalStateException e){
                logger.catching(e);
            }
            catch(IOException e){
                quantity.decrementAndGet();
                e.printStackTrace();
                logger.catching(e);
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
            logger.info("закрытие сервера");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AtomicInteger getQuantity() {
        return quantity;
    }
}


