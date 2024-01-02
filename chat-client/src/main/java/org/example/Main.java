package org.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Client starting...");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите своё имя: ");
//        Укажем своё имя
            String name = scanner.nextLine();
//            1400 - только для подключения
//            создаем соединение с сервером
            Socket socket = new Socket("localhost", 1400);
            Client client = new Client(socket, name);
//            посмотрим данные по портам (remote - удаленный)
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }
    }
}