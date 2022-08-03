package com.opw.financemessage.socket;

import com.opw.financemessage.factory.SystemParameters;
import org.apache.tomcat.jni.Time;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.Theme;

import java.io.*;
import java.net.Socket;

@Component
public class SocketIO {
    private Socket socket;
    private BufferedInputStream input;
    private PrintWriter output;
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketIO.class);

    private SystemParameters parameters = new SystemParameters();
    private String ip = (String)((JSONObject)parameters.getSystemParameters().get("socket")).get("ip");
    private int port = (int)(long)((JSONObject)parameters.getSystemParameters().get("socket")).get("port");

    public SocketIO() {
        try {
            this.socket = new Socket(ip, port);
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public void sendMessage(String message) {
        try {
//            LOGGER.info("Send message");
            if (socket.isConnected()) {

                output.print(message);
                output.flush();
            } else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public String getMessage() {


//        String respond = "";
//        char[] result = new char[600];
//        try {
//            input.read(result);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        LOGGER.info("Get message");
//        respond = new String(result);


//            respond = input.readLine();
        StringBuilder respond = new StringBuilder();
        try {
            int i ;
//            Thread.sleep(1000);
            do{
                i = input.read();
                respond.append((char) i);
            }while (input.available() !=0);
        } catch (IOException e) {
            closeElements(socket, input, output);
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        LOGGER.info(Thread.currentThread().getName());
        return respond.toString();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() {
//        System.out.println("hello world, I have just started up");
//    }

    public void closeElements(Socket socket, BufferedInputStream input, PrintWriter output) {
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

    public void reconnect() throws IOException {
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

        try {
            this.socket = new Socket("10.145.48.94", 40007);
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedInputStream getInput() {
        return input;
    }

    public void setInput(BufferedInputStream input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

}
