package com.opw.financemesage.socket;

import com.opw.financemesage.config.ConfigMapper;
import com.opw.financemesage.factory.Processor;
import com.opw.financemesage.models.MessageISO;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
@Component
public class SocketIO {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public SocketIO(){
        try {
            this.socket = new Socket("10.145.48.94" ,40007);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(),true);
        }
        catch (IOException e){
            e.printStackTrace();
            closeElements(socket, input, output);
        }
    }

    public void sendMessage(String message){
        try{
            output.println(message);
        }catch (Exception e){
            e.printStackTrace();
            closeElements(socket, input,output);
        }
    }

    public String getMessage(){
        String messageRes = null;
        try{
            messageRes =  input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        closeElements(socket,input,output);
        return messageRes;
    }

    public void closeElements(Socket socket, BufferedReader input,PrintWriter output){
        try{
            if (socket != null)
                socket.close();
            if(input != null)
                input.close();
            if(output != null)
                output.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        SocketIO socketIO = new SocketIO();
//        String message = "026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA";
//        socketIO.sendMessage(message);
//        String remessage = socketIO.getMessage();
//        System.out.println(remessage);
//        ConfigMapper configMapper = new ConfigMapper();
//        Processor test = new Processor();
//        test.getInstance(configMapper.configMaper());
//        MessageISO messageISO = test.parsMessage(remessage);
//        messageISO.parsElement();
//    }
}
