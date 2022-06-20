package com.opw.financemesage.socket;

import com.opw.financemesage.models.MessageISO;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
@Repository
public class SocketIO {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public SocketIO(){
        try {
            this.socket = new Socket("way4", 1234);
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e){
            closeElements(socket, input, output);
        }
    }

    public void sendMessage(MessageISO messageISO){
        try{
                output.writeObject(messageISO);
        }catch (IOException e){
            e.printStackTrace();
        }
        closeElements(socket,input,output);
    }

    public MessageISO getMessage(){
        MessageISO messageISO;
        try{
            messageISO = (MessageISO) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        closeElements(socket,input,output);
        return messageISO;
    }

    public void closeElements(Socket socket, ObjectInputStream input,ObjectOutputStream output){
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
