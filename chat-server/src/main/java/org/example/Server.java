package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
//    метод, который будет слушать клиентов
    public void runServer() {
        try {
            while (!serverSocket.isClosed()) {
    //  сервер слушает порт пока кто-то не подключится
//      serverSocket.accept() переводит поток в режим ожидания подключения нового сокета
                Socket socket = serverSocket.accept();
                ClientManager clientManager = new ClientManager(socket);
                System.out.println("Подключен новый клиент!");
//                запускаем свой поток под клиентского менеджера
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        }
        catch (IOException e){
            closeSocket();
        }
    }

    private void closeSocket() {
        try{
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
