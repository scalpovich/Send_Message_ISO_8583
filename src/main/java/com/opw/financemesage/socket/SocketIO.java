package com.opw.financemesage.socket;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;


@Component
public class SocketIO {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public SocketIO() {
        try {
            this.socket = new Socket("10.145.48.94", 40007);
            this.socket.setSoTimeout(50000);
            this.output = new PrintWriter(socket.getOutputStream(),true);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public void sendMessage(String message) {
        try {
            System.out.println("Send message");
            if (socket.isConnected()){
                output.print(message);
                output.flush();
                System.out.println("Message sent");
            }else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public String getMessage() {
        System.out.println("reading");
        try {
            String respond = "";
            char[] result = new char[300];
            input.read(result);
            respond = new String(result);

            System.out.println(respond);
            System.out.println(socket.isConnected());
            return respond;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeElements(Socket socket, BufferedReader input, PrintWriter output) {
        try {
            if (socket != null)
                socket.close();
            if (input != null)
                input.close();
            if (output != null)
                output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }
}
