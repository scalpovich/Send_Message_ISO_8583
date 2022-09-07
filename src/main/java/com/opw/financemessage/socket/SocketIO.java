package com.opw.financemessage.socket;

import com.opw.financemessage.entity.MessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@EnableScheduling
public class SocketIO {
    private Socket socket;
    private BufferedInputStream input;
    private PrintWriter output;
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketIO.class);

    private String id;
    private String ip;
    private int port;
    private boolean connected = false;

    private MessageEntity messageEntity = new MessageEntity();


//    public void connect(){
//        try {
//            JSONObject objSocket = getObjSocket();
//            this.ip =  (String)(objSocket.get("ip"));
//            this.port = (int)(long)objSocket.get("port");
//            this.socket = new Socket(ip, port);
//            this.connected = true;
//            this.output = new PrintWriter(socket.getOutputStream(), true);
//            this.input = new BufferedInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public SocketIO(String id, String ip, int port ){
        try {
            this.id = id;
            this.ip =  ip;
            this.port = port;
            this.socket = new Socket(ip, port);
            this.connected = true;
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disConnect(){
        closeElements(socket,input,output);
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
        try {
            String respond = "";
            byte[] lent = new byte[4];
            input.read(lent);
            String lengt = new String(lent);
            byte[] message = new byte[Integer.parseInt(lengt)];
            input.read(message);
            String messageContent = new String(message);
            respond = lengt + messageContent;
            return respond;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeElements(Socket socket, BufferedInputStream input, PrintWriter output) {
        try {
            if (socket != null)
                socket.close();
            if (input != null)
                input.close();
            if (output != null)
                output.close();
            this.connected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedDelay = 1)
    @EventListener(ApplicationReadyEvent.class)
    private void getMessageAuto(){
        if(input == null && output == null)
            return;
        while (true) {
            String messageReceive = getMessage();
            LOGGER.info(messageReceive);
            messageEntity.addMessage(messageReceive);
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

//        JSONObject objSocket = getObjSocket();

        try {
//            this.ip = (String)(objSocket.get("ip"));
//            this.port = (int)(long)(objSocket.get("port"));
            this.socket = new Socket(ip, port);
            this.output = new PrintWriter(socket.getOutputStream(), true);
            this.input = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }
//    public boolean isSocketChange (){
//        JSONObject objSocket = getObjSocket();
//        if(!((String) objSocket.get("ip")).equals(ip) || port != (int)((long)objSocket.get("port"))){
//            closeElements(socket,input, output);
//            return true;
//        }
//        return false;
//    }

//    public JSONObject getObjSocket(){
//        JSONParser parser = new JSONParser();
//        try {
//            FileReader reader = new FileReader("src/main/resources/SystemParameter.json");
//            Object obj = parser.parse(reader);
//            JSONObject listObject = (JSONObject) obj;
//            JSONObject objSocket = (JSONObject) listObject.get("socket");
//            return objSocket;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void buildMapSocket(){
//        JSONObject obj = getObjSocket();
//        JSONArray arrIssuer = (JSONArray) obj;
//        arrIssuer.forEach(i -> {
//            String id = (String) ((JSONObject) i).get("id");
//            int port = (int)(long) ((JSONObject) i).get("port");
//            String ip = (String) ((JSONObject) i).get("ip");
//            try {
//                mapSocket.put(id,new Socket(ip, port));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
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

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
