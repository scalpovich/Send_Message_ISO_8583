package com.opw.financemesage.socket;

import java.io.*;
import java.net.Socket;

public class Acquirer {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;


    public Acquirer(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer= new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            // Gracefully close everything.
            closeEverything(socket, reader, writer);
        }
    }

    // Sending a message isn't blocking and can be done without spawning a thread, unlike waiting for a message.
    public void sendMessage(String message) {
        try {
            // While there is still a connection with the server, continue to scan the terminal and then send the message.
                writer.println(message);
        } catch (Exception e) {
            // Gracefully close everything.
            closeEverything(socket, reader, writer);
        }
    }
    public String getMessage(){
        String resMessage;
        try {
             resMessage =  reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeEverything(socket,reader,writer);
        return resMessage;
    }

    // Listening for a message is blocking so need a separate thread for that.


    public void closeEverything(Socket socket, BufferedReader reader, PrintWriter writer) {

        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run the program.
    public static void main(String[] args) throws IOException {

        // Create a socket to connect to the server.
        Socket socket = new Socket("localhost", 1234);

        // Pass the socket and give the client a username.
        Acquirer client = new Acquirer(socket);
        String message = "026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA";
        // Infinite loop to read and send messages.
        client.sendMessage(message);
        System.out.println(client.getMessage());

    }
}
