package com.opw.financemesage.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
            closeEverything(socket,reader,writer);
            throw new RuntimeException(e);
        }

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
        String message = "026802007ABA448128E0D0021697040900320033123000000000100000000000100000001126042633000000019206601126331126112660110210006970495279704090032003312=550650098200000457636400000001AB                                              BNV 704704704546694DDEC17E7CD016AAcBVwGgYaBiLAAE";
        // Infinite loop to read and send messages.
        Scanner input = new Scanner(System.in);
        while(true){
            String nhap = input.next();
            client.sendMessage(message);
            System.out.println("sented");
            System.out.println(client.getMessage());
        }

    }
}
