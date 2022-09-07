package com.opw.financemessage.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ManageSocket {
    private Map<String, SocketIO> socketMap = new HashMap<String, SocketIO>();
//    @Autowired
    private List<SocketIO> socketIOList;
    @Autowired
    public ManageSocket(List<SocketIO> socketIOList){
        this.socketIOList = socketIOList;
//        System.out.println(socketIOList.size());
        for (int i = 0; i < socketIOList.size(); i++) {
            SocketIO socketIO = socketIOList.get(i);
            socketMap.put(socketIO.getId(), socketIO);
        }
    }

    public List<SocketIO> getSocketIOList() {
        return socketIOList;
    }

    public SocketIO getSocketByID(String id){
        if(!socketMap.containsKey(id))
            return null;
        return socketMap.get(id);
    }
}
