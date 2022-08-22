//package com.opw.financemessage.socket;
//
//import java.io.*;
//import java.net.Socket;
//
//public class HandleAcquirer implements  Runnable{
//
//
//    private Socket socket;
//    private BufferedReader reader;
//    private PrintWriter writer;
//
//    public HandleAcquirer(Socket socket) {
//        try {
//            this.socket = socket;
//            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.writer= new PrintWriter(socket.getOutputStream(), true);
//        } catch (IOException e) {
//            closeEverything(socket, reader, writer);
//        }
//    }
//
//    @Override
//    public void run() {
//        String request;
//        SocketIO socketIO = null;
//        try {
//            socketIO = new SocketIO();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        try {
//                request = reader.readLine();
//                socketIO.sendMessage(request);
//                writer.println(socketIO.getMessage());
//            } catch (IOException e) {
//                // Close everything gracefully.
//                closeEverything(socket, reader, writer);
//            }
//    }
//
//    // Helper method to close everything so you don't have to repeat yourself.
//    public void closeEverything(Socket socket, BufferedReader reader, PrintWriter writer) {
//
//        try {
//            if (reader != null) {
//                reader.close();
//            }
//            if (writer != null) {
//                writer.close();
//            }
//            if (socket != null) {
//                socket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
