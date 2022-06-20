package com.opw.financemesage.socket;

import com.opw.financemesage.models.MessageISO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.Socket;
@Configuration
public class SocketIO {
    private Socket socket;
    private BufferedReader input;
    private BufferedWriter output;

    public SocketIO(){
        try {
            this.socket = new Socket("way4", 1234);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e){
            closeElements(socket, input, output);
        }
    }

    public void sendMessage(String message){
        try{
            output.write(message);
            output.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        closeElements(socket,input,output);
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

    public void closeElements(Socket socket, BufferedReader input,BufferedWriter output){
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

}
