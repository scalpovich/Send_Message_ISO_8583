//package com.opw.financemessage.socket;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Reciever {
//    private final ServerSocket serverSocket;
//
//    public Reciever(ServerSocket serverSocket) {
//        this.serverSocket = serverSocket;
//    }
//
//    public void startServer() {
//        try {
//            // Listen for connections (clients to connect) on port 1234.
//            while (!serverSocket.isClosed()) {
//                Socket socket = serverSocket.accept();
//                System.out.println("A new acquirer has connected!");
//                HandleAcquirer clientHandler = new HandleAcquirer(socket);
//                Thread thread = new Thread(clientHandler);
//                thread.start();
//                System.out.println("started new thread");
//            }
//        } catch (IOException e) {
//            closeServerSocket();
//        }
//    }
//
//    public void closeServerSocket() {
//        try {
//            if (serverSocket != null) {
//                serverSocket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(1234);
//        Reciever server = new Reciever(serverSocket);
//        server.startServer();
//        System.out.println("close server");
//    }
//}
