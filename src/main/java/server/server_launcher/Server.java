package server.server_launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            System.out.println("Начало работы");
            socket = new ServerSocket(port);
            setOpen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void consoleStart() {
        new ConsoleCommand().start();
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    ExecutorService executorService = Executors.newCachedThreadPool();

    public ArrayBlockingQueue<String> serverList = new ArrayBlockingQueue<String>(10);// список всех нитей

    public int applyConnection() {

        while (isOpen) {
//            try {
//
//                ClientWorkingThread clientWorkingThread = new ClientWorkingThread(socket.accept(), serverList);// придумать выход не через IOException при команде exit
//                serverList.add(clientWorkingThread);
//                //              quantity++;
//                System.out.println("Количество людей на сервере "+(++quantity));
////                    qwantityLabel.setText((String.valueOf(quantity)));
//                executorService.submit(clientWorkingThread);//исполняет асинхронный код в одном или нескольких потоках
//            } catch(IOException e){
//                e.printStackTrace();
//                break;
//            }


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
    public class ConsoleCommand extends Thread {
        @Override
        public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String command;

            try {
                while (true) {
                    command=input.readLine(); // ждем сообщения с с консоли
                    if (command.equals("exit")) {
                        setClose(); // харакири
                        input.close();

                        System.out.println(command +" выполнена");

                        System.exit(1);
                        break;
                    }
                    System.out.println("Команда "+command+" не распознана"); // пишем сообщение с сервера на консоль

                }
            } catch (IOException e) {
                setClose();
            }
        }
    }

}