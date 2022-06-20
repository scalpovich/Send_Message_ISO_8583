package com.opw.financemesage.services.Impl;

import com.opw.financemesage.models.MessageISO;
import com.opw.financemesage.services.MessageService;
import com.opw.financemesage.socket.SocketIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ImplMessageService implements MessageService {

    @Autowired
    private SocketIO socketIO;


    @Override
    public MessageISO sendMessage(MessageISO message) {
        try {
            socketIO.sendMessage(message);
            return socketIO.getMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
