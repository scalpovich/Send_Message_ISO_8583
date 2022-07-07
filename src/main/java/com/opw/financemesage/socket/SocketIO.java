package com.opw.financemesage.socket;

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
////        SocketIO socketIO = new SocketIO();
////        String message = "026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA";
////        socketIO.sendMessage(message);
////        String remessage = socketIO.getMessage();
////        System.out.println(remessage);
////        ConfigMapper configMapper = new ConfigMapper();
////        Processor test = new Processor();
////        test.getInstance(configMapper.configMaper());
////        MessageISO messageISO = test.parsMessage(remessage);
////        messageISO.parsElement();
//
//        String message = "026802007ABA448128E0D0021697040932704448260100000000100000000000100000000524085210000000015371571552100524052460110210006970468279704093270444826=290150057100000050754500000001AB                                              BNV 704704704DB0B60B3204663F5016AAcB6wDcYKtpWwAA";
//        ConfigMapper configMapper = new ConfigMapper();
//        Processor test = new Processor();
//        test.getInstance(configMapper.configMaper());
//
//        List<DataReceive> list = new ArrayList<>();
//        list.add(new DataReceive(0,"0200"));
//        list.add(new DataReceive(1,"1"));
//        list.add(new DataReceive(2,"9704093270444826"));
//        list.add(new DataReceive(3,"010000"));
//        list.add(new DataReceive(4,"000010000000"));
//        list.add(new DataReceive(5,"000010000000"));
//        list.add(new DataReceive(7,"0524085210"));
//        list.add(new DataReceive(9,"00000001"));
//        list.add(new DataReceive(11,"537157"));
//        list.add(new DataReceive(12,"155210"));
//        list.add(new DataReceive(13,"0524"));
//        list.add(new DataReceive(15,"0524"));
//        list.add(new DataReceive(18,"6011"));
//        list.add(new DataReceive(22,"021"));
//        list.add(new DataReceive(25,"00"));
//        list.add(new DataReceive(32,"970468"));
//        list.add(new DataReceive(35,"9704093270444826=2901500571"));
//        list.add(new DataReceive(37,"000000507545"));
//        list.add(new DataReceive(41,"00000001"));
//        list.add(new DataReceive(42,"AB             "));
//        list.add(new DataReceive(43,"                                 BNV 704"));
//        list.add(new DataReceive(49,"704"));
//        list.add(new DataReceive(50,"704"));
//        list.add(new DataReceive(52,"DB0B60B3204663F5"));
//        list.add(new DataReceive(63,"AAcB6wDcYKtpWwAA"));
//
//        String json = "[";
//        for(int i=0; i<list.size(); i++){
//            json += String.format("{\"id\": %d, \"value\": \"%s\"}, ", list.get(i).getId(), list.get(i).getValue());
//        }
//        json = json.substring(0, json.length() - 2);
//        json += "]";
//        System.out.println(json);
//    }
}
