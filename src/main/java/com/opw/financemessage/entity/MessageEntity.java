package com.opw.financemessage.entity;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class MessageEntity {
    private static Queue<String> messageQueue = new LinkedList<>();

    public void addMessage(String message){
        messageQueue.add(message);
    }

    public String getMessageInQueue(){
        return messageQueue.poll();
    }
}
